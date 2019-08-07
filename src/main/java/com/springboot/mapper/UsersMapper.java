package com.springboot.mapper;

import com.springboot.entity.User;

public interface UsersMapper {
    User findByName(String name);
    User findByid(int id);
}
