package yjt.ons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Ons发送消息
 *
 * @author chengxu
 */
@Controller
public class OnsController {

    @Autowired
    private MessageProducer messageProducer;

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public @ResponseBody String send(@RequestParam(required = false) String messageBody) {
        StringBuilder result = new StringBuilder("send");

        if (messageBody == null) {
            messageBody = "default message";
        }

        if (messageProducer.sendMessage(messageBody)) {
            result.append(" success! ");
        } else {
            result.append(" fail! ");
        }
        result.append("<br>");
        result.append("<hr>");
        result.append("[<a href=\"/ons\"> Dev Home </a>]");
        result.append("&nbsp");
        result.append("[<a href=\"/\"> Home </a>]");

        return result.toString();
    }
}
