package com.cqu.login.controller;

import javax.servlet.http.HttpSession;

import com.cqu.login.pojo.User;

import org.springframework.stereotype.Controller;

@Controller
public class LoginController {
    //模拟账号密码
    private static Set<User> dbUsers;
    static {
        dbUsers = new HashSet<>();
        dbUsers.add(new User().setId(1).setUsername("admin").setPassword("admin"));
    }

    public String doLogin(User user,HttpSession session){
        String target = (String) session.getAttribute("target");
        //重定向到target地址
        return "redirect" + target;
    }
}
