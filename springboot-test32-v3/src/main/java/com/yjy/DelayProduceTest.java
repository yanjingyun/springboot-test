package com.yjy;

import com.yjy.mq.SpringTransactionProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DelayProduceTest {

    @Autowired
    private SpringTransactionProducer springTransactionProducer;

    @Test
    public void send() {
        springTransactionProducer.sendMsg("spring-tx-my-topic","事务消息");
    }
}
