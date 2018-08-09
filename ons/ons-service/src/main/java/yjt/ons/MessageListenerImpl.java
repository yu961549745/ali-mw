package yjt.ons;

import com.alibaba.boot.ons.annotation.Subscription;
import com.aliyun.openservices.ons.api.Action;
import com.aliyun.openservices.ons.api.ConsumeContext;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.MessageListener;

/**
 * ONS消息消费者的示例，需要实现MessageListener接口，并通过@Subscription标注需要监听的topic和expression。详情见
 * http://gitlab.alibaba-inc.com/middleware-container/pandora-boot/wikis/spring-boot-ons
 *
 * @author chengxu
 */
@Subscription(topic = MessageProducer.TOPIC, expression = MessageProducer.TAG)
public class MessageListenerImpl implements MessageListener {

    private volatile Message lastReceivedMessage;

    public Message getLastReceivedMessage() {
        return lastReceivedMessage;
    }

    @Override
    public Action consume(Message message, ConsumeContext context) {
        if (dealWithMessage(message)) {
            // 处理消息，消费成功
            return Action.CommitMessage;
        } else {
            // 消费失败，返回后消息会被重投
            return Action.ReconsumeLater;
        }
    }

    /**
     * 处理消息
     */
    private boolean dealWithMessage(Message message) {
        try {
            lastReceivedMessage = message;
            System.out.println("receive success! " + message.getMsgID());
            System.out.println("Topic: " + message.getTopic());
            System.out.println("MessageType: " + message.getTag());
            System.out.println("Key:" + message.getKey());
            System.out.println("User Properties: " + message.getUserProperties());
            System.out.println("Body: " + new String(message.getBody(), "UTF-8"));
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
