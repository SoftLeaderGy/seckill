package com.yang.seckilldemo.controller.businessController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yang.seckilldemo.exception.GlobalException;
import com.yang.seckilldemo.pojo.Order;
import com.yang.seckilldemo.pojo.SeckillOrder;
import com.yang.seckilldemo.pojo.SkillMassage;
import com.yang.seckilldemo.pojo.User;
import com.yang.seckilldemo.rabbitMQ.MQSender;
import com.yang.seckilldemo.service.GoodsService;
import com.yang.seckilldemo.service.OrderService;
import com.yang.seckilldemo.service.SeckillOrderService;
import com.yang.seckilldemo.service.UserService;
import com.yang.seckilldemo.utils.CookieUtil;
import com.yang.seckilldemo.vo.GoodsVo;
import com.yang.seckilldemo.vo.RespBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.yang.seckilldemo.vo.RespBeanEnum.*;

/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2022/04/02/20:57
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController implements InitializingBean {

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private SeckillOrderService seckillOrderService;

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private OrderService orderService;

    private Map<Long,Boolean> emptyMap = new HashMap<>();
    @Autowired
    private MQSender mqSender;
    @RequestMapping("/doSeckill")
    @ResponseBody
    public RespBean doSeckill(HttpServletRequest request, HttpServletResponse response, Long goodsId, Model model){

        User user = userService.queryUserByCookie(request,response, CookieUtil.getCookieValue(request,"userTicket"));
        GoodsVo goodsVo = goodsService.queryGoodsDetail(goodsId);
        if(goodsVo == null){
            model.addAttribute("errmsg",GOODS_NULL.getMessage());
            return RespBean.error(GOODS_NULL);
        }
        if(goodsVo.getStockCount() < 1){
            model.addAttribute("errmsg",EMPTY_STOCK.getMessage());
            return RespBean.error(EMPTY_STOCK);
        }
        QueryWrapper<SeckillOrder> seckillOrderQueryWrapper = new QueryWrapper<>();
        seckillOrderQueryWrapper.eq("goods_id",goodsId)
                        .eq("user_id",user.getId());
//        SeckillOrder seckillOrder = seckillOrderService.getOne(seckillOrderQueryWrapper);

        // 从redis缓存中获取秒杀订单信息, 判断是否重复抢购
        SeckillOrder seckillOrder = (SeckillOrder) redisTemplate.opsForValue().get("order:" + goodsVo.getId() + "user:" + user.getId());
        if(seckillOrder != null){
            model.addAttribute("errmsg",REPEATE_ERROR.getMessage());
            return RespBean.error(REPEATE_ERROR);
        }

        /**
         * 系统优化思路：
         *      将商品的库存数量放入redis中，在redis中进行预减库存，并将秒杀的请求放入rabbitMQ队列中，进行排队秒杀。
         *      并且，返回在秒杀结果出来之前需要返回给客户端"正在排队处理中"，并异步处理队列中的订单，并且结果出来之前
         *      需要轮询的查询秒杀结果，直到查询结果，返回给客户端，秒杀是否成功。
         */

        if(emptyMap.get(goodsId)){
            return RespBean.error(EMPTY_STOCK);
        }
        // redis中预减库存
        // 将redis中的key为"goodsId:" + goodsId 减1 ，返回的是减少之后的库存
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Long decrement = valueOperations.decrement("goodsId:" + goodsId);
        if(decrement < 0){
            model.addAttribute("errmsg",EMPTY_STOCK.getMessage());
            // 如果是小于0的话，把库存的数量在加1 就会变成0，好看些！
            valueOperations.increment("goodsId:" + goodsId);
            emptyMap.put(goodsId,true);
            return RespBean.error(EMPTY_STOCK);
        }

        // 将秒杀的请求信息放入MQ队列中去
        SkillMassage skillMassage = new SkillMassage(user, goodsVo);
        // 将消息发送出去，做异步处理
        mqSender.sendSkillMsg(skillMassage);
        // 先给用户返回信"0"，如果用户接到的是0 就说明在排队，
        // 等队列中的消息处理
        return RespBean.success(0);


        /**
         * 优化前直接去秒杀
         */
        /*
        Order order = orderService.seckill(goodsVo,user);
        model.addAttribute("order",order);
        model.addAttribute("goods",goodsVo);
        model.addAttribute("user",user);
        return "orderDetail";
         */
    }


    /**
     * 实现 InitializingBean 后重写的方法
     * 在Spring容器加载此bean的时候会执行 该方法
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
    //  在系统初始化的时候，将数据库的库存加入到redis中
        // 查询出数据库中的商品信息
        List<GoodsVo> goodsVoList = goodsService.findGoodsVo();
        // 将每一个商品的 以"goodsId"+ goodsVo.getId() 作为redis的key 对应商品的库存数量 存入redis中
        goodsVoList.forEach(goodsVo -> {
            redisTemplate.opsForValue().set("goodsId"+ goodsVo.getId(),goodsVo.getStockCount());
            emptyMap.put(goodsVo.getId(),false);
        });
    }
}
