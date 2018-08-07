package yjt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 演示返回页面
 *
 * @author 鱼泡
 */
@Controller
public class PageController {
    @RequestMapping("/hello")
    public String hello(String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }
}
