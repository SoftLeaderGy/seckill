package com.yang.seckilldemo.service;

import com.yang.seckilldemo.pojo.Order;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yang.seckilldemo.pojo.SeckillOrder;
import com.yang.seckilldemo.pojo.User;
import com.yang.seckilldemo.vo.GoodsVo;

/**
* @author yang
* @description 针对表【t_order】的数据库操作Service
* @createDate 2022-03-27 22:46:30
*/
public interface OrderService extends IService<Order> {
    Order seckill(GoodsVo goodsVo, User user);
}
