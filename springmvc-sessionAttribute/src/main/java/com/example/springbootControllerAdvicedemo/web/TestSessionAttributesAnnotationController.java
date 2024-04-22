package com.example.springbootControllerAdvicedemo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@SessionAttributes("name")
public class TestSessionAttributesAnnotationController {

    @GetMapping("/sessionAttributesTest")
    public String sessionAttributeTest(Model model) {
        model.addAttribute("name", "add weimin in model");
        return "redirect:/get";
    }


    @GetMapping("/get")
    @ResponseBody
    public String get(HttpServletRequest request, HttpSession session) {
        return request.getAttribute("name") +"; "+ session.getAttribute("name");
    }
}
