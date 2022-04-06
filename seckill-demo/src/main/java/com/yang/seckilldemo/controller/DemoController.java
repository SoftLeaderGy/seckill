package com.yang.seckilldemo.controller;

import com.yang.seckilldemo.rabbitMQ.MQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2022/03/22/15:38
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private MQSender mqSender;

    @RequestMapping("/hello")
    public String hello(Model model){
        model.addAttribute("name","gy");
        return "hello";
    }

    @RequestMapping("/test")
    public void testMQ(){
        mqSender.send("hello");
    }

    @RequestMapping("/testFanoutExchangeMQ")
    public void testFanoutExchangeMQ(){
        mqSender.sendExchange("hello");
    }

    /**
     * 测试发送到路由健为 RouteRed的队列中去
     */
    @RequestMapping("/testDirectExchangeMQForRouteRed")
    public void testDirectExchangeMQRouteRed(){
        mqSender.sendDirectExchangeForRouteRed("hello - 路由健为 RouteRed");
    }

    /**
     * 测试发送到路由健为 RouteGreen的队列中去
     */
    @RequestMapping("/testDirectExchangeMQForRouteGreen")
    public void testDirectExchangeMQForRouteGreen(){
        mqSender.sendDirectExchangeForRouteGreen("hello - 路由健为 RouteGreen");
    }
}

