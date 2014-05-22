package com.ehome.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

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
    public String hello(HttpServletRequest request, HttpServletResponse response, Object handler, Model m) {
        System.out.println("hello()...");
        m.addAttribute("user", "单车上的理想");
        String requestUri = new UrlPathHelper().getLookupPathForRequest(request);
        m.addAttribute("appUrl", requestUri);
        m.addAttribute("appName", "问答");
        m.addAttribute("appHtml", "<div><h1><@person.component/></h1></div>" + requestUri);

        Map map = new HashMap();
        map.put("key", "map的值...");
        m.addAttribute("map", map);

        return "hello";
    }

}
