package com.example.springbootlogdemo.demos.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BasicController {

    private static final Log logger = LogFactory.getLog(BasicController.class);
    @RequestMapping("/user")
    @ResponseBody
    public User user() {
        User user = new User();
        user.setName("theonefx");
        user.setAge(666);

        logger.info(user);
        logger.error(user);
        logger.warn(user);
        return user;
    }
}
