package com.yang.seckilldemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yang.seckilldemo.pojo.Goods;
import com.yang.seckilldemo.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author yang
* @description 针对表【t_goods】的数据库操作Mapper
* @createDate 2022-03-27 22:46:30
* @Entity com.yang.seckilldemo.pojo.Goods
*/
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
    List<GoodsVo> findGoodsVo();

    GoodsVo queryGoodsDetail(Long goodsId);
}
