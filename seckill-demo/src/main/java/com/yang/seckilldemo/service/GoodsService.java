package com.yang.seckilldemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yang.seckilldemo.pojo.Goods;
import com.yang.seckilldemo.vo.GoodsVo;

import java.util.List;

/**
* @author yang
* @description 针对表【t_goods】的数据库操作Service
* @createDate 2022-03-27 22:46:30
*/
public interface GoodsService{

    /**
     * 获取商品列表
     * @return
     */
    List<GoodsVo> findGoodsVo();
}
