# HSF
HSF 是阿里的微服务实现框架

1. 定义一个接口
```java
public interface HelloService {
    String sayHello(String name);
}
```
2. 添加一个实现 (Provider), `@HSFProvider` 声明了 HSF 服务的实现接口
```java
@HSFProvider(serviceInterface = HelloService.class)
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Hello, " + name;
    }
}
```
3. 配置一个调用
```java
@Configuration
public class HsfConfig {
    @HSFConsumer
    private HelloService helloService;
}
```
4. 进行调用, `@Autowired` 是 Spring 的自动装配
```java
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
```
5. HSF 发布的相关配置 `application.properties`
```
# hsf配置，详见 http://gitlab.alibaba-inc.com/middleware-container/pandora-boot/wikis/spring-boot-hsf
spring.hsf.group=HSF
spring.hsf.version=1.0.0.DAILY
spring.hsf.timeout=2000
```
