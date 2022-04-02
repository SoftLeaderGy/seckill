package com.yang.seckilldemo.controller.businessController;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.org.apache.xalan.internal.xsltc.dom.StepIterator;
import com.yang.seckilldemo.pojo.Goods;
import com.yang.seckilldemo.service.GoodsService;
import com.yang.seckilldemo.vo.GoodsVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.management.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * (Goods)表控制层
 *
 * @author makejava
 * @since 2022-03-27 23:03:53
 */
@Controller
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

    @RequestMapping("/goodsDetail/{goodsId}")
    public String goodsDetail(Model model, @PathVariable Long goodsId, HttpServletRequest request, HttpServletResponse response){
        GoodsVo goodsVo = goodsService.queryGoodsDetail(goodsId);
        model.addAttribute("goods",goodsVo);
        Integer secKillStatus = 0;

        Integer remainSeconds = 0;
        Date date = new Date();
        if(date.before(goodsVo.getStartDate())){
            // 秒杀还未开始
            remainSeconds = Integer.parseInt(((goodsVo.getStartDate().getTime() - new Date().getTime())/1000) + "");

        }else if(date.after(goodsVo.getStartDate()) && date.before(goodsVo.getEndDate())){
            // 正在进行秒杀
            secKillStatus = 1;
            remainSeconds = 0;
        }else if(date.after(goodsVo.getEndDate())){
            // 秒杀结束
            secKillStatus = 2;
            remainSeconds = -1;
        }
        model.addAttribute("remainSeconds",remainSeconds);
        model.addAttribute("secKillStatus" ,secKillStatus);
        return "goodsDetail";
    }
}

