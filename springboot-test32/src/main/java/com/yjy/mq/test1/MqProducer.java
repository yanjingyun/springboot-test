package com.yjy.mq.test1;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MqProducer {

    private static Logger logger = LoggerFactory.getLogger(MqProducer.class);
    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("Producer");
        producer.setNamesrvAddr("localhost:9876");
        try {
            producer.start();
            logger.info("producer启动成功");
            for (int i = 0; i < 5; i++) {
                Message msg = new Message("TopicTest", "*", "OrderID188", "Hello world".getBytes());
                SendResult result = producer.send(msg);
                logger.info("id：" + result.getMsgId() + " result:" + result.getSendStatus());
            }
        } catch (Exception e) {
            logger.error("发送消息失败，Exception error：" + e);
        } finally {
            producer.shutdown();
        }

    }
}
