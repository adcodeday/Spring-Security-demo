package com.example.demo.service.service;

import com.example.demo.pojo.UserLoginDTO;
import com.example.demo.pojo.User;
public interface UserService {
    User login(UserLoginDTO user);
}

//jdbc:mysql://${dev.datasource.host}:${dev.datasource.port}/${dev.datasource.database}?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true
//jdbc:mysql://${sky.datasource.host}:${sky.datasource.port}/${sky.datasource.database}?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowPublicKeyRetrieval=true