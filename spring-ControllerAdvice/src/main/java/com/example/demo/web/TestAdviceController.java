package com.example.demo.web;

import com.example.demo.pojo.Author;
import com.example.demo.pojo.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class TestAdviceController {

    // 测试全局异常处理
    @GetMapping("/errorTest")
    @ResponseBody
    public String error(@RequestParam("name") String name) {
        if ("null".equals(name)) {
            throw new NullPointerException("null error!");
        }

        if("indexOut".equals(name)){
            throw new IndexOutOfBoundsException("indexOut error!");
        }
        return "hello controller advice";
    }

    // 测试全局数据绑定
    @GetMapping("/mdmap")
    @ResponseBody
    public String hello(Model model) {
        Map<String, Object> map = model.asMap();
        System.out.println(map);
        return "hello controller advice";
    }


    // http://localhost:8080/book?a.name=weimin&a.age=22&b.name=%E4%B8%89%E5%9B%BD%E6%BC%94%E4%B9%89&b.price=100
    @GetMapping("/book")
    @ResponseBody
    public String addBook(@ModelAttribute("b") Book book, @ModelAttribute("a") Author author) {
        System.out.println(book);
        System.out.println(author);
        return "book: " + book.toString() + ", author: " + author.toString();
    }

    // http://localhost:8080/book?name=111&price=100&age=20
//    @GetMapping("/book")
//    @ResponseBody
//    public String addBook( Book book, Author author) {
//        System.out.println(book);
//        System.out.println(author);
//        return "book: " + book.toString() + ", author: " + author.toString();
//    }

}
