package com.yang.seckilldemo.controller;

import com.yang.seckilldemo.service.UserService;
import com.yang.seckilldemo.vo.LoginVO;
import com.yang.seckilldemo.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Description: 登录控制器
 * @Author: Guo.Yang
 * @Date: 2022/03/23/21:32
 */
@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Autowired
    private UserService userService;
    @RequestMapping("/toLogin")
    public String tologin(){
        return "login";
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public RespBean doLogin(@Valid LoginVO loginVO){
        return userService.doLogin(loginVO);
    }

}
