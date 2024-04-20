package com.example.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

// 加解密需要的key，这里必须是16位
@ConfigurationProperties(prefix = "spring.encrypt")
public class EncryptProperties {
    private final static String DEFAULT_KEY = "weimin1234567890";
    private String key = DEFAULT_KEY;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
