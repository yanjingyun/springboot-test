package com.yjy.rabbitmq.simple;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 简单队列模式：一个生产者对应一个消费者
 */
@Component
public class SimpleListener {

	/** 简单队列模式-消费者 */
	// 通过注解自动创建 spring.simple.queue 队列
	@RabbitListener(queuesToDeclare = @Queue("spring.simple.queue"))
	public void listen(String msg) {
		System.out.println("简单队列：消费者接收到消息：" + msg);
	}
}
