package com.yjy.mq;

import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RocketMQMessageListener(topic = "delay-topic", consumerGroup = "delay-group")
public class DelayConsumer implements RocketMQListener<String> {

    private static final Logger log = LoggerFactory.getLogger(DelayConsumer.class);

    @Override
    public void onMessage(String message) {
        log.info("接收消息：{}，时间：{}", message, DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss").format(LocalDateTime.now()));
    }
}
