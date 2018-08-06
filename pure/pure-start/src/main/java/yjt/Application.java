package yjt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.taobao.pandora.boot.PandoraBootstrap;

/**
 * Pandora Boot应用的入口类
 *
 * @author chengxu
 */
@SpringBootApplication(scanBasePackages = {"yjt"})
public class Application {

    public static void main(String[] args) {
        // 启动 Pandora Boot
        PandoraBootstrap.run(args);
        // 启动 Spring Boot
        SpringApplication.run(Application.class, args);
        // Pandora Boot 标记服务启动完成
        PandoraBootstrap.markStartupAndWait();
    }
}
