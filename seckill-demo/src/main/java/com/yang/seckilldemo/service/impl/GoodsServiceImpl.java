package com.yang.seckilldemo.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yang.seckilldemo.pojo.Goods;
import com.yang.seckilldemo.service.GoodsService;
import com.yang.seckilldemo.mapper.GoodsMapper;
import com.yang.seckilldemo.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author yang
* @description 针对表【t_goods】的数据库操作Service实现
* @createDate 2022-03-27 22:46:30
*/
@Service
public class GoodsServiceImpl implements GoodsService{

    @Autowired
    private GoodsMapper goodsMapper;
    @Override
    public List<GoodsVo> findGoodsVo() {
        return goodsMapper.findGoodsVo();
    }
}
