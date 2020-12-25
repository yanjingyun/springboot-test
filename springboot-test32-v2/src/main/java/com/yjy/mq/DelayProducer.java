package com.yjy.mq;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DelayProducer {

    private static final Logger log = LoggerFactory.getLogger(DelayProducer.class);

    @Autowired
    private RocketMQTemplate rocketMQTemplatet;

    public void sendDelayMessage(String topic,String message,int delayLevel){
        SendResult sendResult = rocketMQTemplatet.syncSend(topic, MessageBuilder.withPayload(message).build(), 2000, delayLevel);
        log.info("发送消息：{}，时间：{}", message, DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss").format(LocalDateTime.now()));
        log.info("发送消息结果：{}",sendResult);
    }
}
