package com.example.demo.service.Controller;

import com.example.demo.pojo.User;
import com.example.demo.pojo.UserLoginDTO;
import com.example.demo.service.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/auth/login")
    public User login(UserLoginDTO user){
        log.info("111111");
        return userService.login(user);
    }
}
