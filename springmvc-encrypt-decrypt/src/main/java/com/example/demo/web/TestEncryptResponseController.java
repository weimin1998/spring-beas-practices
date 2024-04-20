package com.example.demo.web;

import com.example.demo.annotation.Encrypt;
import com.example.demo.pojo.RespBean;
import com.example.demo.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试加密response
 */
@RestController
public class TestEncryptResponseController {

    // 返回值类型不是RespBean
    // 也没有@Encrypt
    @RequestMapping("/encrypt1")
    public User encrypt1() {
        User user = new User();
        user.setName("tom");
        user.setAge(20);
        return user;
    }

    // 没有@Encrypt
    @RequestMapping("/encrypt2")
    public RespBean encrypt2() {
        User user = new User();
        user.setName("tom");
        user.setAge(20);
        return RespBean.ok("ok", user);
    }

    @RequestMapping("/encrypt3")
    @Encrypt
    public RespBean encrypt3() {
        User user = new User();
        user.setName("tom");
        user.setAge(20);
        return RespBean.ok("ok", user);
    }
}

