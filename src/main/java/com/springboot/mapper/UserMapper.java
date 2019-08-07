package com.springboot.mapper;

import com.springboot.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface UserMapper {
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "password", column = "password")
    })
    @Select("select id,name,password from users where name=#{name}")
    User findByName(String name);

    @Select("select id,name,password,jues from users where id=#{id}")
    User findByid(int id);
}
