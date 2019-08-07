package com.springboot.service.impl;

import com.springboot.entity.User;
import com.springboot.mapper.UserMapper;
import com.springboot.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper usersMapper;
    @Override
    public User findByName(String name) {
        return usersMapper.findByName(name);
    }

    @Override
    public User findByid(int id) {
        return usersMapper.findByid(id);
    }

    @Override
    public String alogin(String name, String password, Model model) {
        /**
         * 使用Shiro编写认证操作
         */
        //1.获取Subject
        Subject subject= SecurityUtils.getSubject();

        //2.封装用户数据
        UsernamePasswordToken token=new UsernamePasswordToken(name,password);

        //3.执行登录方法
        try {
            //登录成功
            subject.login(token);
            User usera= (User) subject.getPrincipal();
            Session session=subject.getSession();
            session.setAttribute("token",usera);
            /*User user1= userService.findByName(user.getName());
            User dbuser=userService.findByid(user1.getId());*/
           /* if(dbuser.getJues()==1)
                return "user/add";
            if (dbuser.getJues()==2)
                return "user/update";*/
            return "redirect:/thy";
        }catch (UnknownAccountException e){
            //登录失败:用户名不存在
            model.addAttribute("msg","用户名不存在");
            return "login";
        }catch (IncorrectCredentialsException e){
            //登录失败:密码错误
            model.addAttribute("msg","密码错误");
            return "login";
        }

    }

}
