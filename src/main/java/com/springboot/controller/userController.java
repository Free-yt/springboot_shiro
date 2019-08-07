package com.springboot.controller;

import com.springboot.entity.User;
import com.springboot.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class userController {
    /*
        测试
     */
    @RequestMapping("/hello")
    @ResponseBody
    public String Controller(){
        return "hello";
    }

    /**
     * 测试thymeleaf
     */
    @RequestMapping("/thy")
    public String Thymeleaf(Model model,UsernamePasswordToken token){
        model.addAttribute("name","zhuzhu");
        return "test";
    }

    @RequestMapping("/add")
    public String add(){

        return "user/add";
    }
    @RequestMapping("/update")
    public String update(){
        return "user/update";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @Autowired
    private UserService userService;
    @RequestMapping("/logingo")
    public String logingo(User user, Model model, HttpSession session){

        String string= userService.alogin(user.getName(),user.getPassword(),model);
        //if(user.getName()==nulluser.getPassword(),model);

       return string;
    }

    @RequestMapping("/a")
    @ResponseBody
    public User a(String name){

        User user=userService.findByName("free");
        return user;
    }

    @RequestMapping("/noAuth")
    public String noauth(){

        return "noauth";
    }

    @RequestMapping("/addtoGo")
    public String usertoGo(){

        return "usergo/addtoGo";
    }
}
