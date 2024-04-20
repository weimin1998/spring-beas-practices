package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FlashMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class OrderController {

    // 跳转页面
    @GetMapping("/orderPage")
    public String orderPage(Model model) {
        return "order";
    }

    @PostMapping("/order")
    public String order(HttpServletRequest request) {

        // 首先在 order 接口中，获取到 flashMap 属性，
        // 然后存入需要传递的参数，这些参数最终会被 SpringMVC 自动放入重定向接口的 Model 中，
        // 这样我们在 orderlist 接口中，就可以获取到该属性了。
        FlashMap flashMap = (FlashMap) request.getAttribute(DispatcherServlet.OUTPUT_FLASH_MAP_ATTRIBUTE);
        flashMap.put("name", "weimin");
        flashMap.put("test", "flashMap");

        return "redirect:orderList";
    }

    @PostMapping("/order1")
    public String order1(RedirectAttributes redirectAttributes) {

        // RedirectAttributes 中有两种添加参数的方式：
        //addFlashAttribute：将参数放到 flashMap 中。
        //addAttribute：将参数放到 URL 地址中。
        redirectAttributes.addFlashAttribute("name", "weimin");
        redirectAttributes.addAttribute("test", "RedirectAttributes");

        return "redirect:orderList";
    }

    @GetMapping("/orderList")
    @ResponseBody
    public String orderList(Model model) {
        return model.getAttribute("name") + (String) model.getAttribute("test");
    }

    // 为什么会有sessionid
    // http://localhost:8080/orderList;jsessionid=11DCD4322E4E59E42716A8F46AD8A62E
}
