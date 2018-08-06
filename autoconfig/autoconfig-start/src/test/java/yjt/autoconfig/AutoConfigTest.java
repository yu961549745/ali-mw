package yjt.autoconfig;

import java.io.IOException;
import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.taobao.pandora.boot.test.junit4.PandoraBootRunner;

import junit.framework.TestCase;

/**
 * AutoConfig的测试
 * 
 * @author chengxu
 */
@RunWith(PandoraBootRunner.class)
public class AutoConfigTest {

    @Test
    public void testGet() {
        Properties appProperties = new Properties();
        try {
            appProperties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("auto-config-demo.properties"));
            TestCase.assertEquals("autoconfig", appProperties.getProperty("autoconfig.value"));
        } catch (IOException e) {
            TestCase.fail(e.getMessage());
        }
    }
}
