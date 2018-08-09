package yjt.diamond;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import yjt.Application;
import com.taobao.pandora.boot.test.junit4.DelegateTo;
import com.taobao.pandora.boot.test.junit4.PandoraBootRunner;

import junit.framework.TestCase;

/**
 * Diamond的测试
 * 
 * @author chengxu
 */
@RunWith(PandoraBootRunner.class)
@DelegateTo(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { Application.class })
public class DiamondTest {

    @Autowired
    DiamondDataCallbackDemo callback;

    @Autowired
    private DiamondDemo diamondDemo;

    @Test
    public void testGet() {
        TestCase.assertNotNull(diamondDemo.getStringValue());
    }

    @Test
    public void testListener() {
        // 增加listener后，需要等一会儿才能收到配置信息
        // TestCase.assertNotNull(callback.getReceivedData());
    }
}
