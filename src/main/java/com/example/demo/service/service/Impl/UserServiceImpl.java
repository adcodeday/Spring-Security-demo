package com.example.demo.service.service.Impl;

import com.example.demo.pojo.User;
import com.example.demo.pojo.UserLoginDTO;
import com.example.demo.service.mapper.UserMapper;
import com.example.demo.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public User login(UserLoginDTO user) {
        User resultuser=userMapper.login(user);
        return resultuser;
    }
}
