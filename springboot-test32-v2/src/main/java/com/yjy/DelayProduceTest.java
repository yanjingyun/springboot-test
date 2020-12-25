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

    @Test
    public void sendDelayMessage() {
        delayProducer.sendDelayMessage("delay-topic","测试延迟消息",5);
    }
}
