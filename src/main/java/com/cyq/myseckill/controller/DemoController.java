package com.cyq.myseckill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测试
 * @author chenyongquan
 * @version 1.0
 * @date 2021/5/16-18:59
 * @description com.cyq.myseckill.controller
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    /**
     * 测试页面跳转
     * @param model
     * @return
     */
    @RequestMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("name", "张三");
        return "hello";
    }

}
