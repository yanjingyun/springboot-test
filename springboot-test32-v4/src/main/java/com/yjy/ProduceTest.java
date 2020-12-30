package com.yjy;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProduceTest {

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Test
    public void send() {
        // 连续发送100条消息
        for (int i = 0; i < 100; i++) {
            rocketMQTemplate.send("xf-topic", MessageBuilder.withPayload("消息" + i).build());
        }
    }
}
