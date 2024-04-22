package com.example.springbootControllerAdvicedemo.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;


@Controller
public class TestSessionAttributeAnnotationController {


    @GetMapping("/sessionAttributeTest")
    @ResponseBody
    public String sessionAttributeTest(@SessionAttribute("name") String name) {
        return name;
    }
}
