package com.yang.seckilldemo.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.seckilldemo.pojo.SeckillOrder;
import com.yang.seckilldemo.service.SeckillOrderService;
import com.yang.seckilldemo.mapper.SeckillOrderMapper;
import org.springframework.stereotype.Service;

/**
* @author yang
* @description 针对表【t_seckill_order】的数据库操作Service实现
* @createDate 2022-03-27 22:46:30
*/
@Service
public class SeckillOrderServiceImpl extends ServiceImpl<SeckillOrderMapper, SeckillOrder>
implements SeckillOrderService{

}
