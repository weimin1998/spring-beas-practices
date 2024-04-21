package com.example.demo.config;

import com.example.demo.pojo.RespBean;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

//全局异常处理
@ControllerAdvice
public class MyGlobalExceptionHandler {

    // 全局异常处理
    @ExceptionHandler(value = NullPointerException.class)
    public ModelAndView customNullPointerException(Exception e) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", e.getMessage());
        mv.setViewName("errorPage");
        return mv;
    }


    // 全局异常处理
    @ExceptionHandler(value = IndexOutOfBoundsException.class)
    @ResponseBody
    public RespBean customIndexOutException(Exception e) {
        return RespBean.error(e.getMessage());
    }


    // 全局异常处理
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public RespBean customException(Exception e) {
        return RespBean.error(e.getMessage());
    }

}
