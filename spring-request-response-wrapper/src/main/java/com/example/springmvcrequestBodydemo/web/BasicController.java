
package com.example.springmvcrequestBodydemo.web;

import com.example.springmvcrequestBodydemo.pojo.RespBean;
import com.example.springmvcrequestBodydemo.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BasicController {


    @PostMapping("/hello")
    @ResponseBody
    public String hello(@RequestBody User user1, @RequestBody User user2) {
        return "Hello user1:" + user1.toString() + ", Hello user2:" + user2.toString();
    }

    @PostMapping("/index")
    @ResponseBody
    public RespBean index(@RequestBody User user) {
        return RespBean.ok("hello, " + user.getName());
    }

}
