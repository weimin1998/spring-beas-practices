package com.example.demo.demos.quartz.config.job;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyJob1 {
    public void sayHello() {
        System.out.println("MyJob1>>>"+new Date());
    }
}

// 这种job的定义方式:
// 首先将这个 Job 注册到 Spring 容器中。
// 这种定义方式有一个缺陷，就是无法传参。

