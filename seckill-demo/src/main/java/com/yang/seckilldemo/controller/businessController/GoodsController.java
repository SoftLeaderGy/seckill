package com.yang.seckilldemo.controller.businessController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yang.seckilldemo.pojo.Goods;
import com.yang.seckilldemo.service.GoodsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.management.Query;

/**
 * (Goods)表控制层
 *
 * @author makejava
 * @since 2022-03-27 23:03:53
 */
@RestController
public class GoodsController {
    /**
     * 服务对象
     */
    @Resource
    private GoodsService goodsService;

    public String toList(Model model){
        model.addAttribute("goodsList",goodsService.findGoodsVo());
        return "goodsList";
    }
}

