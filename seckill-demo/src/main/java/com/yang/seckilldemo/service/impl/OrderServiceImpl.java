package com.yang.seckilldemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.seckilldemo.mapper.SeckillGoodsMapper;
import com.yang.seckilldemo.mapper.SeckillOrderMapper;
import com.yang.seckilldemo.pojo.Order;
import com.yang.seckilldemo.pojo.SeckillGoods;
import com.yang.seckilldemo.pojo.SeckillOrder;
import com.yang.seckilldemo.pojo.User;
import com.yang.seckilldemo.service.GoodsService;
import com.yang.seckilldemo.service.OrderService;
import com.yang.seckilldemo.mapper.OrderMapper;
import com.yang.seckilldemo.utils.UUIDUtil;
import com.yang.seckilldemo.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
* @author yang
* @description 针对表【t_order】的数据库操作Service实现
* @createDate 2022-03-27 22:46:30
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
implements OrderService{

    @Autowired
    private SeckillGoodsMapper seckillGoodsMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private SeckillOrderMapper seckillOrderMapper;
    @Override
    public Order seckill(GoodsVo goodsVo, User user) {
        QueryWrapper<SeckillGoods> seckillGoodsQueryWrapper = new QueryWrapper<>();
        seckillGoodsQueryWrapper.eq("goods_id",goodsVo.getId());
        SeckillGoods seckillGoods = seckillGoodsMapper.selectOne(seckillGoodsQueryWrapper);
        seckillGoods.setStockCount(seckillGoods.getStockCount() - 1);
        seckillGoodsMapper.updateById(seckillGoods);

        // 生成订单
        Order order = new Order();
        order.setUserId(user.getId());
        order.setGoodsId(goodsVo.getId());
        order.setDeliveryAddrId(0L);
        order.setGoodsName(goodsVo.getGoodsName());
        order.setGoodsCount(1);
        order.setGoodsPrice(goodsVo.getGoodsPrice());
        order.setOrderChannel(1);
        order.setStatus(0);
        order.setCreateDate(new Date());
        orderMapper.insert(order);

        // 生成秒杀
        SeckillOrder seckillOrder = new SeckillOrder();
        seckillOrder.setUserId(user.getId());
        seckillOrder.setOrderId(order.getId());
        seckillOrder.setGoodsId(goodsVo.getId());
        seckillOrderMapper.insert(seckillOrder);
        return order;
    }
}
