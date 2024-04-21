package com.example.springbootjdbcTemplatedemo;

import com.example.springbootjdbcTemplatedemo.pojo.User;
import com.example.springbootjdbcTemplatedemo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringbootJdbcTemplateDemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    UserService userService;

    @Test
    public void add(){
        User user = new User("1","weimin","shanghai");
        int i = userService.addUser(user);
        System.out.println(i);
    }

    @Test
    public void update(){
        User user = new User("1","weimin","shanxi");
        int i = userService.updateUserById(user);
        System.out.println(i);
    }

    @Test
    public void delete(){
        int i = userService.deleteUserById("1");
        System.out.println(i);
    }

    @Test
    public void query(){
        List<User> allUsers = userService.getAllUsers();
        System.out.println(allUsers);
    }
}
