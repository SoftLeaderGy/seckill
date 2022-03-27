package com.yang.seckilldemo.controller.businessController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Author: Guo.Yang
 * @Date: 2022/03/22/15:38
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    @RequestMapping("/hello")
    public String hello(Model model){
        model.addAttribute("name","gy");
        return "hello";
    }
}
