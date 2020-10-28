package com.yjy.rabbitmq.work;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 工作模式：
 * 1、一个生产者对应多个消费者
 * 2、生产者发送到队列中的消息，由服务器平均分配给不同消费者进行消费
 */
@Component
public class WorkListener {
	
	// 通过注解自动创建 spring.work.queue 队列
	@RabbitListener(queuesToDeclare = @Queue("spring.work.queue"))
	public void listen(String msg) {
		System.out.println("work模式: 消费者1接收到消息：" + msg);
	}

	// 创建两个队列共同消费
	@RabbitListener(queuesToDeclare = @Queue("spring.work.queue"))
	public void listen2(String msg) {
		System.out.println("work模式: 消费者2接收到消息：" + msg);
	}
}
