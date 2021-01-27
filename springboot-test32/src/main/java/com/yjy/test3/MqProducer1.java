package com.yjy.test3;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

public class MqProducer1 {
    public static void main(String[] args) {
        DefaultMQProducer producer = new DefaultMQProducer("a_test1_producer");
        producer.setNamesrvAddr("192.168.145.128:9876");
        try {
            producer.start();
            int index = 0;
            while (true) {
                Message msg = new Message("test3_topic", "*", "orderId:" + index, (index + "").getBytes());
                SendResult result = producer.send(msg);
                System.out.println(result);
                index++;
                Thread.sleep(500);

                // 仅发送20条记录
                if (index == 20) break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }

    }
}
