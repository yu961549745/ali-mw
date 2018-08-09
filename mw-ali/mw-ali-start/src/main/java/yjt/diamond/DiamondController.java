package yjt.diamond;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * diamond配置信息
 *
 * @author chengxu
 */
@Controller
@RequestMapping(value = "diamond")
public class DiamondController {

    @Autowired
    private DiamondDemo diamondDemo;

    @Autowired
    private ConfigBean configBean;

    @RequestMapping(value = "testProperties")
    public @ResponseBody String testProperties() {
        StringBuilder result = new StringBuilder();
        result.append("stringValue: " + diamondDemo.getStringValue());
        result.append("<br>");
        result.append("intValue: " + diamondDemo.getIntValue());
        result.append("<br>");
        result.append("booleanValue: " + diamondDemo.getBooleanValue());
        addLink(result);

        return result.toString();
    }

    @RequestMapping(value = "configFromListener")
    public @ResponseBody String configFromListener() {
        StringBuilder result = new StringBuilder();
        result.append(configBean);
        addLink(result);
        return result.toString();
    }

    private void addLink(StringBuilder result) {
        result.append("<br>");
        result.append("<hr>");
        result.append("[<a href=\"/diamond\"> Dev Home </a>]");
        result.append("&nbsp");
        result.append("[<a href=\"/\"> Home </a>]");
    }
}
