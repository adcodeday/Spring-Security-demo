package com.example.demo.service.mapper;

import com.example.demo.pojo.User;
import com.example.demo.pojo.UserLoginDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where username=#{username} and password=#{password}")
    User login(UserLoginDTO user);
    @Select("select * from user where username=#{username}")
    User get(String username);

}
