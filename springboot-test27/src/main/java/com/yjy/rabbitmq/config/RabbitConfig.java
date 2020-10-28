package com.yjy.rabbitmq.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * RabbitMQ配置类
 * 1.定义两个队列
 * 2.定义扇形交换机
 * 3.交换机和队列绑定
 * 4.创建生产者并生产消息
 * 5.创建两个消费者分别监听两个队列
 */
@Configuration
public class RabbitConfig {

	public final static String queueNameA = "first-queue";
	public final static String queueNameB = "second-queue";
	
	public final static String exchangeName = "exchange-name";

	/** 队列A */
	@Bean("queueA")
	public Queue queueA() {
		Map<String, Object> map = new HashMap<>();
		// 消息过期时长，10秒过期
		map.put("x-message-ttl", 10000);
		// 队列中最大消息条数，10条
		map.put("x-max-length", 10);
		// 第一个参数，队列名称
		// 第二个参数，durable：持久化
		// 第三个参数，exclusive：排外的，
		// 第四个参数，autoDelete：自动删除
		Queue queue = new Queue(queueNameA, true, false, false, map);
		return queue;
	}

	/** 队列B */
	@Bean("queueB")
	public Queue queueB() {

		Map<String, Object> map = new HashMap<>();
		// 消息过期时长，10秒过期
		map.put("x-message-ttl", 10000);
		// 队列中最大消息条数，10条
		map.put("x-max-length", 10);
		// 第一个参数，队列名称
		// 第二个参数，durable：持久化
		// 第三个参数，exclusive：排外的，
		// 第四个参数，autoDelete：自动删除
		Queue queue = new Queue(queueNameB, true, false, false, map);
		return queue;
	}

	@Bean
	public FanoutExchange fanoutExchange() {
		// 第一个参数，交换机名称
		// 第二个参数，durable，是否持久化
		// 第三个参数，autoDelete，是否自动删除
		FanoutExchange fanoutExchange = new FanoutExchange(exchangeName, true, false);
		return fanoutExchange;
	}

	@Bean
	public Binding bindingA(@Qualifier("queueA") Queue queueA, FanoutExchange fanoutExchange) {
		Binding binding = BindingBuilder.bind(queueA).to(fanoutExchange);
		return binding;
	}

	@Bean
	public Binding bindingB(@Qualifier("queueB") Queue queueB, FanoutExchange fanoutExchange) {
		Binding binding = BindingBuilder.bind(queueB).to(fanoutExchange);
		return binding;
	}
}
