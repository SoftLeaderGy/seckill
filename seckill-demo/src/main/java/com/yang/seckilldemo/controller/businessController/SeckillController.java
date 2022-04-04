package com.yang.seckilldemo.controller.businessController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yang.seckilldemo.exception.GlobalException;
import com.yang.seckilldemo.pojo.Order;
import com.yang.seckilldemo.pojo.SeckillOrder;
import com.yang.seckilldemo.pojo.User;
import com.yang.seckilldemo.service.GoodsService;
import com.yang.seckilldemo.service.OrderService;
import com.yang.seckilldemo.service.SeckillOrderService;
import com.yang.seckilldemo.service.UserService;
import com.yang.seckilldemo.utils.CookieUtil;
import com.yang.seckilldemo.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.yang.seckilldemo.vo.RespBeanEnum.*;

/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2022/04/02/20:57
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

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
    @RequestMapping("/doSeckill")
    public String doSeckill(HttpServletRequest request, HttpServletResponse response, Long goodsId, Model model){
        User user = userService.queryUserByCookie(request,response, CookieUtil.getCookieValue(request,"userTicket"));
        GoodsVo goodsVo = goodsService.queryGoodsDetail(goodsId);
        if(goodsVo == null){
            model.addAttribute("errmsg",GOODS_NULL.getMessage());
            return "secKillFail";
        }
        if(goodsVo.getStockCount() < 1){
            model.addAttribute("errmsg",EMPTY_STOCK.getMessage());
            return "secKillFail";
        }
        QueryWrapper<SeckillOrder> seckillOrderQueryWrapper = new QueryWrapper<>();
        seckillOrderQueryWrapper.eq("goods_id",goodsId)
                        .eq("user_id",user.getId());
//        SeckillOrder seckillOrder = seckillOrderService.getOne(seckillOrderQueryWrapper);

        // 从redis缓存中获取秒杀订单信息,
        SeckillOrder seckillOrder = (SeckillOrder) redisTemplate.opsForValue().get("order:" + goodsVo.getId() + "user:" + user.getId());
        if(seckillOrder != null){
            model.addAttribute("errmsg",REPEATE_ERROR.getMessage());
            return "secKillFail";
        }
        Order order = orderService.seckill(goodsVo,user);
        model.addAttribute("order",order);
        model.addAttribute("goods",goodsVo);
        model.addAttribute("user",user);
        return "orderDetail";
    }
}
