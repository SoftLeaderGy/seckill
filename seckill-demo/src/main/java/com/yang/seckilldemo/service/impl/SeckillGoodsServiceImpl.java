package com.yang.seckilldemo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.seckilldemo.pojo.SeckillGoods;
import com.yang.seckilldemo.service.SeckillGoodsService;
import com.yang.seckilldemo.mapper.SeckillGoodsMapper;
import org.springframework.stereotype.Service;

/**
* @author yang
* @description 针对表【t_seckill_goods】的数据库操作Service实现
* @createDate 2022-03-27 22:46:30
*/
@Service
public class SeckillGoodsServiceImpl extends ServiceImpl<SeckillGoodsMapper, SeckillGoods>
implements SeckillGoodsService{

}
