package com.yjy.mq;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(topic = "spring-tx-my-topic", consumerGroup = "tx-consumer", selectorExpression = "*")
public class SpringTxConsumer implements RocketMQListener<String> {
    @Override
    public void onMessage(String msg) {
        System.out.println("接收到消息 -> " + msg);
    }
}
