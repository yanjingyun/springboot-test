package com.yjy.mq;

import com.yjy.entity.Order;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderProducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;


    // 发送消息
    public void sendMsgOrder(Order order) {
        // 第二个参数为 hashkey，根据其哈希值取模后确定发送到哪一个队列
        // 一般情况下，可以使用订单号、商品号、用户编号，只要订单号在同一队列，通过队列的天生顺序执行，保证消息能顺序消费
        /**
         * Producer 可以根据定义 MessageQueueSelector 消息队列选择策略，选择 Topic 下的队列
         * 目前提供了三种策略：
         * 1）SelectMessageQueueByHash ，基于 hashKey 的哈希值取余，选择对应的队列
         * 2）SelectMessageQueueByRandom ，基于随机的策略，选择队列
         * 3）SelectMessageQueueByMachineRoom ，机器空间
         */
        rocketMQTemplate.sendOneWayOrderly("order-topic", order, order.getOrderId());
    }
}
