package com.yjy.mq;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class SpringTransactionProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送消息
     */
    public void sendMsg(String topic, String msg) {
        Message<String> message = MessageBuilder.withPayload(msg).build();
        // 注：myTransactionGroup要和@RocketMQTransactionListener(txProducerGroup = "myTransactionGroup")定义的一致
        this.rocketMQTemplate.sendMessageInTransaction("myTransactionGroup",topic,message, null);
        System.out.println("发送消息成功");
    }
}
