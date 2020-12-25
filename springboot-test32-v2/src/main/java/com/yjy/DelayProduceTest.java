package com.yjy;

import com.yjy.mq.DelayProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DelayProduceTest {

    @Autowired
    private DelayProducer delayProducer;

    // 测试发送演示消息，1分钟后才会给到消费端
    @Test
    public void sendDelayMessage() {
        delayProducer.sendDelayMessage("delay-topic","sendDelayMessage",5);
    }

    // 测试发送演示消息，自定义延时时间，90s后才会给到消费端
    @Test
    public void sendCustomeDelayMessage() {
        delayProducer.sendDelayMessage("delay-topic","sendCustomeDelayMessage",1);
    }
}
