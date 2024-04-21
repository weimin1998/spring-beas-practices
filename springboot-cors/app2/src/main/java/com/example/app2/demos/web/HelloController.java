package com.example.app2.demos.web;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    // 表示接受来自http://localhost:8081 地址的跨域请求
    //@CrossOrigin(value = "http://localhost:8081")
    public String hello() {
        return "get hello";
    }

    @PostMapping("/hello")
    //@CrossOrigin(value = "http://localhost:8081")
    public String hello2() {
        return "post hello";
    }
}