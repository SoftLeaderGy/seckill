package com.yang.seckilldemo.controller.businessController;

import com.yang.seckilldemo.pojo.User;
import com.yang.seckilldemo.service.GoodsService;
import com.yang.seckilldemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description: 登录成功后 跳转商品列表页面
 * @Author: Guo.Yang
 * @Date: 2022/03/26/23:30
 */
@Controller
@RequestMapping("/goods")
public class GoodsListController {

    @Autowired
    private UserService userService;
    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/toList")
    public String toList(HttpServletResponse response, HttpServletRequest request,HttpSession session, Model model, @CookieValue("userTicket") String ticket){
//        if(StringUtils.isEmpty(ticket)){
//            return "login";
//        }
////        User user = (User) session.getAttribute(ticket);
//        User user = userService.queryUserByCookie(request, response, ticket);
//        if(user == null){
//            return "login";
//        }
//        model.addAttribute("user",user);
        model.addAttribute("goodsList",goodsService.findGoodsVo());
        return "goodsList";
    }
}
