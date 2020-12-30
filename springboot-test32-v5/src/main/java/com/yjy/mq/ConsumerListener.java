package com.yjy.mq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.annotation.MessageModel;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RocketMQMessageListener(topic = "broadcasting-topic",
        consumerGroup = "broadcasting-consumer-group",
        messageModel = MessageModel.BROADCASTING // 设置为广播消费
)
public class ConsumerListener implements RocketMQListener<String> {
    @Override
    public void onMessage(String str) {
        System.out.printf("时间：%s, 消息：%s %n", LocalDateTime.now(), str);
    }
}
