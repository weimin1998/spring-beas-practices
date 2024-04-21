package com.example.app2.demos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// 直接加在controller太麻烦，可以加在配置中
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                // 逗号分割
                .allowedOrigins("http://localhost:8081", "http://localhost:8082")
                .allowedMethods("GET")
                .allowedHeaders("*");

        // /** 表示本应用的所有方法都会去处理跨域请求，
        // allowedMethods 表示允许通过的请求方式，
        // allowedHeaders 则表示允许的请求头，即，请求头中必须带的key
        // 经过这样的配置之后，就不必在每个方法上单独配置跨域了。
    }
}
