package com.yjy.mq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RocketMQMessageListener(topic = "xf-topic", consumerGroup = "xf-consumer-group")
public class XfConsumerListener implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {
    @Override
    public void onMessage(String str) {
        System.out.printf("时间：%s, 消息：%s %n", LocalDateTime.now(), str);
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        // 每次拉取的间隔，单位为毫秒
        consumer.setPullInterval(2000);
        // 设置每次从队列中拉取的消息数为2
        // 每次从每个队列的拉取数，Consumer每次拉取消息总数=所有broker上队列数 * pullBatchSize
        // 例如，2个broker，每个broker的队列数为4，每次从队列拉取数为3，则consumer每次拉取消息总数=2*4*3=24条
        consumer.setPullBatchSize(2);
    }
}
