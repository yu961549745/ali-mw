package yjt.hsf.provider;

import com.alibaba.boot.hsf.annotation.HSFProvider;
import yjt.hsf.HelloService;

/**
 * 通过HSFProvider声明一个HSF服务,详情见 http://gitlab.alibaba-inc.com/middleware-container/pandora-boot/wikis/spring-boot-hsf
 *
 * @author chengxu
 */
@HSFProvider(serviceInterface = HelloService.class)
public class HelloServiceImpl implements HelloService {

    @Override
    public String sayHello(String name) {
        return "Hello, " + name;
    }
}
