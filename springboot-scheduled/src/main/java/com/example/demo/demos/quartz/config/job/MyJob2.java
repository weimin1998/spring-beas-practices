package com.example.demo.demos.quartz.config.job;

import com.example.demo.demos.service.HelloService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class MyJob2 extends QuartzJobBean {
    HelloService helloService;

    public HelloService getHelloService() {
        return helloService;
    }

    public void setHelloService(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        helloService.sayHello();
    }
}
// 第二种定义方式，则是继承 QuartzJobBean 并实现默认的方法：
// 和第1种方式相比，这种方式支持传参，任务启动时，executeInternal 方法将会被执行。