package com.yjy.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

	// 定义延时队列
	@Bean("delayQueue")
	public Queue delayQueue() {
		return QueueBuilder.durable("delayQueue")
				// 如果消息过时，则会被投递到当前对应的my-dlx-exchange交换机中
				.withArgument("x-dead-letter-exchange", "my-dlx-exchange")
				// 如果消息过时，my-dlx-exchange会根据routing-key-delay投递消息到对应的队列
				.withArgument("x-dead-letter-routing-key", "routing-key-delay").build();
	}
	
	// 定义死信队列
	@Bean("dlxQueue")
	public Queue dlxQueue() {
		return QueueBuilder.durable("my-dlx-queue").build();
	}
	
	// 定义死信交换机
	@Bean("dlxExchange")
	public Exchange dlxExchange() {
		return ExchangeBuilder.directExchange("my-dlx-exchange").build();
	}
	
	// 绑定死信队列与交换机
	@Bean("dlxBinding")
	public Binding dlxBinding(@Qualifier("dlxExchange") Exchange exchange, @Qualifier("dlxQueue") Queue queue) {
		return BindingBuilder.bind(queue).to(exchange).with("routing-key-delay").noargs();
	}
	
}
