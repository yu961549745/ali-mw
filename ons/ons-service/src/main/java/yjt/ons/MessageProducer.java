package yjt.ons;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.Producer;
import com.aliyun.openservices.ons.api.SendResult;

/**
 * ONS的消息生产者示例。通过@Autowired注入ONS的消息生产者Producer，并通过它发送消息。详情见
 * http://gitlab.alibaba-inc.com/middleware-container/pandora-boot/wikis/spring-boot-ons
 *
 * @author chengxu
 */
@Component
public class MessageProducer {

    public final static String TOPIC = "topic-ons-test";
    public final static String TAG   = "NM-ons-test";

    @Autowired
    private Producer producer;

    public boolean sendMessage(String messageBody) {
        Message message = new Message();
        /*
         * 必选字段:topic, tag, body
         */
        message.setTopic(TOPIC);
        message.setTag(TAG);
        message.setBody(messageBody.getBytes());

        /*
         * 可选字段：key, userProperties
         */
        message.setKey("123456789");

        Properties userProperties = new Properties();
        userProperties.setProperty("key", "value");
        message.setUserProperties(userProperties);

        /*
         * 发送成功则没有异常抛出，失败则抛异常
         */
        try {
            SendResult sendResult = producer.send(message);
            System.out.println("send success! " + sendResult.getMessageId());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
