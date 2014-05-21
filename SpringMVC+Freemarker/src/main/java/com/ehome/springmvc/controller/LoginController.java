package com.ehome.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @version V1.0
 * @Project: MyTools
 * @Title:
 * @Package PACKAGE_NAME
 * @Description:
 * @Author xiaolei-0228@163.com
 * @Date 2014/5/21 0021 17:46
 * @Copyright: 2014 ihome.com
 */
@Controller
public class LoginController {
    @RequestMapping("/home")
    public String home(Model model) {
        System.out.println("进入SpringMVC Controller...");
        model.addAttribute("message", "hello, world");
        return "home";
    }

    @RequestMapping("/hello")
    public void hello() {
        System.out.println("hello()...");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("result", "hello");
    }

}
