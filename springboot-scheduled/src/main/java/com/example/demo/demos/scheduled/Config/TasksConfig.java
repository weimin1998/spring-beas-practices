package com.example.demo.demos.scheduled.Config;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

//@Configuration
public class TasksConfig {
    // 每5秒打印一次当前时间
    @Scheduled(cron = "0/5 * * * * *")
    public void cron() {
        System.out.println(new Date());
    }

    @Scheduled(fixedRate = 2000)
    public void fixedRate() {
        System.out.println("fixedRate>>>" + new Date());
    }

    @Scheduled(fixedDelay = 2000)
    public void fixedDelay() {
        System.out.println("fixedDelay>>>" + new Date());
    }

    @Scheduled(initialDelay = 2000, fixedDelay = 2000)
    public void initialDelay() {
        System.out.println("initialDelay>>>" + new Date());
    }


}
