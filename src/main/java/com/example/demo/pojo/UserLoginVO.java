package com.example.demo.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginVO implements Serializable {
    private String username;
    private String token;

    public UserLoginVO(String username, String token) {
        this.username=username;
        this.token=token;
    }
}
