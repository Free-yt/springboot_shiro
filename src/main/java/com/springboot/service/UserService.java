package com.springboot.service;

import com.springboot.entity.User;
import org.springframework.ui.Model;

public interface UserService {
    User findByName(String name);
    User findByid(int id);
    String alogin(String name,String password, Model model);
}
