package com.yjy.mq;

import com.yjy.entity.Order;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Component;

@Component
@RocketMQMessageListener(consumerGroup = "order-consumer-group",
        topic = "order-topic",
        consumeMode = ConsumeMode.ORDERLY)
public class OrderConsumer implements RocketMQListener<Order> {

    @Override
    public void onMessage(Order order) {
        System.out.printf("order:%s, time:%d%n", order, System.currentTimeMillis());
    }
}
