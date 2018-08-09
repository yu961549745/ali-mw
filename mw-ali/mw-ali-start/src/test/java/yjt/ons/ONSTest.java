package yjt.ons;

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
 * 通过ONS的消息生产者，测试消息的发送
 * 
 * @author chengxu
 */
@RunWith(PandoraBootRunner.class)
@DelegateTo(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = { Application.class })
public class ONSTest {

    @Autowired
    private MessageProducer messageProducer;

    @Autowired
    private MessageListenerImpl listener;

    @Test
    public void test() {
        TestCase.assertTrue(messageProducer.sendMessage("messageBody"));
        // 发送消息后，消费者要等一会儿才能收到
        // TestCase.assertNotNull(listener.getLastReceivedMessage());
    }
}
