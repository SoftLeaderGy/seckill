package com.yang.seckilldemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.seckilldemo.pojo.Order;
import com.yang.seckilldemo.service.OrderService;
import com.yang.seckilldemo.mapper.OrderMapper;
import org.springframework.stereotype.Service;

/**
* @author yang
* @description 针对表【t_order】的数据库操作Service实现
* @createDate 2022-03-27 22:46:30
*/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
implements OrderService{

}
