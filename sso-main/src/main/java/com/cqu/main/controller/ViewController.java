package com.cqu.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.Map;

// 页面调整逻辑
@Controller
@RequestMapping("/view")
public class ViewController {
    @Autowired
    private RestTemplate restTemplate;

    private final String LOGIN_INFO_URL = "http://localhost:9000/login/info?token=";
    
    // 跳转到登陆界面
    @GetMapping("/index")
    public String toIndex(@CookieValue(value = "TOKEN",required = false)Cookie cookie, HttpSession session){
        //验证cokie
        if (cookie != null){
            String token = cookie.getValue();
            if (!StringUtils.isEmpty(token)){
                Map map = restTemplate.getForObject(LOGIN_INFO_URL + token, Map.class);
                session.setAttribute("loginUser",map);
            }
        }
        return "index";
    }
}
