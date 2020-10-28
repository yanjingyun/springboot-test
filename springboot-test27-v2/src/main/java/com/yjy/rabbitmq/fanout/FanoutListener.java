package com.yjy.rabbitmq.fanout;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 订阅模式（Fanout）-消费者
 * 1、可以有多个消费者
 * 2、每个消费者有自己的队列
 * 3、每个队列都要绑定到Exchange交换机
 * 4、生产者发送的消息，只能发送到交换机，交换机来决定要发给哪个队列，生产者无法决定
 * 5、交换机把消息发送给绑定过的所有队列
 * 6、队列的消费者都能拿到消息，实现一条消息被多个消费者消息
 */
@Component
public class FanoutListener {

	// 消费者1
	@RabbitListener(
			bindings = @QueueBinding(value = @Queue(value = "spring.fanout.queueA", durable = "true"), 
			exchange = @Exchange(value = "spring.fanout.exchange", ignoreDeclarationExceptions = "true", type = ExchangeTypes.FANOUT)))
	public void listen(String msg) {
        System.out.println("订阅模式Fanout：消费者1接收到消息：" + msg);
    }
	
	// 消费者2
	@RabbitListener(bindings = @QueueBinding(value = @Queue(value = "spring.fanout.queueB", durable = "true"),
			exchange = @Exchange(value = "spring.fanout.exchange", ignoreDeclarationExceptions = "true", type = ExchangeTypes.FANOUT)))
	public void listen2(String msg) {
		System.out.println("订阅模式Fanout：消费者2接收到消息：" + msg);
	}
}
