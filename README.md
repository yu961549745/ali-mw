# ali-mw
阿里巴巴中间件使用测试

学习计划
+ Pandora Boot / Spring Boot
+ Spring MVC
+ Velocity
+ HSF
+ MyBatis
+ TDDL


# 快速入门

## Pandora Boot
首先，Pandora Boot 与 Spring Boot 无缝集成，使用 Pandora Boot 的同时也可以充分享受 Spring Boot 的便利；

其次，Pandora Boot 是在 Pandora 的基础之上，发展出的更轻量使用集团中间件的方式；它基于 Pandora 和 Fat Jar 技术，可以直接在 IDE 里启动 Pandora 环境，开发调试等效率都大大提高；

```java
// Spring Boot App 标记
@SpringBootApplication(scanBasePackages = {"yjt"})
public class Application {

    public static void main(String[] args) {
        // 启动 Pandora Boot
        PandoraBootstrap.run(args);
        // 启动 Spring Boot
        SpringApplication.run(Application.class, args);
        // 标记 Pandora Boot 启动完成
        PandoraBootstrap.markStartupAndWait();
    }
}
```

## Spring MVC

