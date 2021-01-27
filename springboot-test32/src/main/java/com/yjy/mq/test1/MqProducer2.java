package com.yjy.mq.test1;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

public class MqProducer2 {
    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("a_test1_producer2");
        producer.setNamesrvAddr("192.168.145.128:9876");
        try {
            producer.start();
            int index = 0;
            while (true) {
                Message msg = new Message("a_test1_topic", "*", "orderId:" + index, (index + "").getBytes());
                SendResult result = producer.send(msg);
                System.out.println(result);
                index++;
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            System.out.println("发送消息失败，Exception error：" + e);
        } finally {
            producer.shutdown();
        }

    }
}
