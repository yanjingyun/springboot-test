package com.yjy.rabbitmq.topic;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 主题模式（topic）-消费者
 * Topic和Direct都是根据routingKey把消息路由到不同的队列，不同的是Topic类型的RoutingKey可使用通配符
 * 规则：1）#：匹配一个多多个词；2）*：匹配恰好一个词
 */
@Component
public class TopicListener {
	
	@RabbitListener(bindings = @QueueBinding(value = @Queue(value = "spring.topic.queue", durable = "true"),
			exchange = @Exchange(value = "spring.topic.exchange", ignoreDeclarationExceptions = "true", type = ExchangeTypes.TOPIC), key = { "person.*" }))
	public void listen(String msg) {
		System.out.println("主题模式（topic）-消费者person接收到消息：" + msg);
	}

	@RabbitListener(bindings = @QueueBinding(value = @Queue(value = "spring.topic.queue", durable = "true"),
			exchange = @Exchange(value = "spring.topic.exchange", ignoreDeclarationExceptions = "true", type = ExchangeTypes.TOPIC), key = { "money.*" }))
	public void listen2(String msg) {
		System.out.println("主题模式（topic）-消费者money接收到消息：" + msg);
	}
}
