package yjt.hsf.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import yjt.hsf.HelloService;

/**
 * 通过HSFConsumer注入hsf服务，详情见 http://gitlab.alibaba-inc.com/middleware-container/pandora-boot/wikis/spring-boot-hsf
 *
 * @author chengxu
 */
@Controller
@RequestMapping(value = "invoke")
public class HsfController {

    @Autowired
    @Qualifier("helloService")
    private HelloService helloService;

    /**
     * 调用hsf服务
     */
    @RequestMapping(value = "common")
    public @ResponseBody String invokeHSF(@RequestParam String name) {
        StringBuilder result = new StringBuilder();
        result.append(helloService.sayHello(name));
        addLink(result);
        return result.toString();
    }

    private void addLink(StringBuilder result) {
        result.append("<br>");
        result.append("<hr>");
        result.append("[<a href=\"/hsf\"> Dev Home </a>]");
        result.append("&nbsp");
        result.append("[<a href=\"/\"> Home </a>]");
    }

}
