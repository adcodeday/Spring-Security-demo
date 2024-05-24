package com.example.demo.service.Controller;

import com.example.demo.Security.auth.JwtUtil;
import com.example.demo.common.Result;
import com.example.demo.pojo.UserLoginDTO;
import com.example.demo.pojo.UserLoginVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;

    }
    @PostMapping(path = "/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDto){
        try {
            //进行验证
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDto.getUsername(), userLoginDto.getPassword()));
            String username = authentication.getName();
            userLoginDto.setPassword("");
            String token = jwtUtil.createToken(userLoginDto);
            UserLoginVO userLoginVO=new UserLoginVO(username,token);
            log.info("登陆成功|||账户：{}|||token：{}",userLoginVO.getUsername(),userLoginVO.getToken());
            return Result.ok(userLoginVO);
        }catch (BadCredentialsException e){
            log.info("登陆失败1");
            return Result.fail();
        }catch (Exception e){
            log.info("登陆失败2");
            return Result.fail();
        }
    }
}
