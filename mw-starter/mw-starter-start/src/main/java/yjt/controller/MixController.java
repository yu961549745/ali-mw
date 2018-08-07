package yjt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Page 和 Response Body 混合
 *
 * @author 鱼泡
 */
@Controller
@RequestMapping("/mix")
public class MixController {
    @RequestMapping("/page")
    public String page() {
        return "hello";
    }

    @RequestMapping("/resp")
    @ResponseBody
    public String resp() {
        return "hello world";
    }
}
