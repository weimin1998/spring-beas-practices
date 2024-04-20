package com.example.demo.web;

import com.example.demo.annotation.Decrypt;
import com.example.demo.pojo.RespBean;
import com.example.demo.pojo.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试解密request
 */
@RestController
public class TestDecryptRequestController {


    @PostMapping("/decrypt1")
    public User decrypt1(@RequestBody User user){
        return user;
    }

    @PostMapping("/decrypt2")
    @Decrypt
    public User decrypt2(@RequestBody User user){
        return user;
    }

    @PostMapping("/decrypt3")
    public User decrypt3(@RequestBody @Decrypt User user){
        return user;
    }
}
