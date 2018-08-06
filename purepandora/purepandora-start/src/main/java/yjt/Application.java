package yjt;

import com.taobao.pandora.boot.PandoraBootstrap;

/**
 * Pandora Boot应用的入口类
 */
public class Application {
    public static void main(String[] args) {
        PandoraBootstrap.run(args);

        PandoraBootstrap.markStartupAndWait();
    }
}
