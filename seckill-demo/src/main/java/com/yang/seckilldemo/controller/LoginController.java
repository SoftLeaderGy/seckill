package com.yang.seckilldemo.controller;

import com.wf.captcha.ArithmeticCaptcha;
import com.yang.seckilldemo.pojo.User;
import com.yang.seckilldemo.service.UserService;
import com.yang.seckilldemo.vo.LoginVO;
import com.yang.seckilldemo.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private RedisTemplate redisTemplate;
    @RequestMapping("/toLogin")
    public String tologin(){
        return "login";
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public RespBean doLogin(@Valid LoginVO loginVO, HttpServletRequest request, HttpServletResponse response){
        return userService.doLogin(loginVO,request,response);
    }

    @RequestMapping(value = "captcha",method = RequestMethod.GET)
    public void Captcha(HttpServletResponse response) throws IOException {

        // 设置相应头信息
        response.setContentType("image/jpg");
        response.setHeader("Pargam","No-cache");
        response.setHeader("Cacal-Control","No-cache");
        response.setDateHeader("Expires",0);

        //  生成验证码
        ArithmeticCaptcha arithmeticCaptcha = new ArithmeticCaptcha(130, 32, 3);

        // 将生成的验证码放入redis中
        redisTemplate.opsForValue().set("captcha:", arithmeticCaptcha.text(),300, TimeUnit.SECONDS);
        // 将生成的验证码以流的形式相应出去
        arithmeticCaptcha.out(response.getOutputStream());
    }

}
