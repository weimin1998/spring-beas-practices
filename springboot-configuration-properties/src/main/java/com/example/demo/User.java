package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;

//@Component
// 将配置文件中user开头的配置绑定到这个类的属性上
@ConfigurationProperties(prefix = "user")
public class User {
    String name;
    Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
