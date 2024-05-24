package com.example.demo.common;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
//为啥不加注解没法给前端认证呢
@Data
public class Result<T> implements Serializable {
    private Integer status;
    private String message;
    private T data;


    static public <T> Result<T> ok(T data){
        Result<T> result = new Result<>();
        result.status=1;
        result.message="ok";
        result.data=data;
        return result;
    }

    static public Result ok() {
        Result result = new Result<>();
        result.status = 1;
        result.message = "ok";
        return result;
    }

    static public Result fail(){
        Result result=new Result<>();
        result.status=0;
        result.message="fail";
        return result;
    }
}
