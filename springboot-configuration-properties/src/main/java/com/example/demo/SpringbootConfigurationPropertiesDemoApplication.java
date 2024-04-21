package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

@EnableConfigurationProperties(User.class)
@SpringBootApplication
public class SpringbootConfigurationPropertiesDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringbootConfigurationPropertiesDemoApplication.class, args);
        User bean = applicationContext.getBean(User.class);
        System.out.println(bean);
    }

}
