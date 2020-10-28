package com.yjy.rabbitmq.direct;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 路由模式(Direct)-消费者
 * 在Fanout模式中，一条消息，会被所有订阅的队列都消费。但是，在某些场景下，我们希望不同的消息被不同的队列消费，这时就要用到Direct类型的Exchange
 * 特点：
 * 1、队列与交换机的绑定，不能是任意绑定了，而是要指定一个RoutingKey（路由key）
 * 2、消息的发送方在 向 Exchange发送消息时，也必须指定消息的 RoutingKey
 * 3、Exchange不再把消息交给每一个绑定的队列，而是根据消息的Routing Key进行判断，只有队列的 Routingkey与消息的 Routing key完全一致，才会接收到消息
 */
@Component
public class DirectListener {
	
	@RabbitListener(bindings = @QueueBinding(value = @Queue(value = "spring.direct.queueA", durable = "true"),
			exchange = @Exchange(value = "spring.direct.exchange", ignoreDeclarationExceptions = "true"), key = { "direct-key1" }))
	public void listen(String msg) {
		System.out.println("路由模式(Direct)-消费者1接收到消息：" + msg);
	}

	// 队列2（第二个人），key值不同，接收不到消息
	@RabbitListener(bindings = @QueueBinding(value = @Queue(value = "spring.direct.queueB", durable = "true"),
			exchange = @Exchange(value = "spring.direct.exchange", ignoreDeclarationExceptions = "true"), key = { "direct-key2" }))
	public void listen2(String msg) {
		System.out.println("路由模式(Direct)-消费者2接收到消息：" + msg);
	}
}
