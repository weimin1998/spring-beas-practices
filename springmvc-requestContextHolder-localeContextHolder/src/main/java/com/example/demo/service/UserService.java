package com.example.demo.service;


import com.example.demo.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public User getUser() {

        // service中获取请求、响应，除了直接从controller中传参，还有下面的方式
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert requestAttributes != null;
        HttpServletRequest request = requestAttributes.getRequest();
        HttpServletResponse response = requestAttributes.getResponse();
        logger.info(">>>>>>>>>>>>>>>request uri: " + request.getRequestURI());
        logger.info(">>>>>>>>>>>>>>>response: " + response);

        // LocaleContextHolder获取locale
        Locale locale = LocaleContextHolder.getLocale();
        logger.info(">>>>>>>>>>>>>>>" + locale);

        // request 获取locale
        logger.info(">>>>>>>>>>>>>>>" + request.getLocale());


        User user = new User();
        user.setName("theonefx");
        user.setAge(666);
        return user;
    }
}
