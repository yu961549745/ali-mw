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
    private HelloService helloService;

    @RequestMapping(value = "common")
    public @ResponseBody
    String invokeHSF(@RequestParam String name) {
        return helloService.sayHello(name);
    }

}
