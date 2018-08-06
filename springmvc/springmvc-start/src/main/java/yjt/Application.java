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
        PandoraBootstrap.run(args);
        SpringApplication.run(Application.class, args);
        PandoraBootstrap.markStartupAndWait();
    }
}
