package com.yang.seckilldemo.controller;

import com.yang.seckilldemo.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @Description: 登录成功后 跳转商品列表页面
 * @Author: Guo.Yang
 * @Date: 2022/03/26/23:30
 */
@Controller
@RequestMapping("/goods")
public class GoodsListController {

    @RequestMapping("/toList")
    public String toList(HttpSession session, Model model,@CookieValue("userTicket") String ticket){
        if(StringUtils.isEmpty(ticket)){
            return "login";
        }
        User user = (User) session.getAttribute(ticket);
        if(user == null){
            return "login";
        }
        model.addAttribute("user",user);
        return "goodsList";
    }
}
