package yjt.hsf;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import yjt.Application;
import com.taobao.pandora.boot.test.junit4.DelegateTo;
import com.taobao.pandora.boot.test.junit4.PandoraBootRunner;

import junit.framework.TestCase;

/**
 * 将HSFConfig中配置的hsf服务注入并测试
 * 
 * @author chengxu
 */
@RunWith(PandoraBootRunner.class)
@DelegateTo(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { Application.class })
public class HSFTest {

    @Autowired
    @Qualifier("helloService")
    private HelloService service;

    @Test
    public void testInvoke() {
        TestCase.assertEquals("Hello, pandora boot", service.sayHello("pandora boot"));
    }

}
