package yjt;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.boot.velocity.annotation.VelocityLayout;

/**
 * @author chengxu
 */
@Controller
public class MainController {

    @GetMapping("/")
    @VelocityLayout("/velocity/layout/index.vm")
    public String root() {
        return "index";
    }

    @GetMapping("/tddl")
    public String tddl() {
        return "tddl";
    }

    @GetMapping("/hsf")
    public String hsf() {
        return "hsf";
    }

    /**
     * 健康检查，系统部署需要 请不要删除！！
     */
    @GetMapping("/checkpreload.htm")
    public @ResponseBody
    String checkPreload() {
        return "success";
    }
}
