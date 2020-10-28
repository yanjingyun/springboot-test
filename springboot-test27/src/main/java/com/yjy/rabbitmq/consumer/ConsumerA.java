package com.yjy.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.yjy.rabbitmq.config.RabbitConfig;

/**
 * 消费者A
 */
@RabbitListener(queues = RabbitConfig.queueNameA)
@Component
public class ConsumerA {

	final Logger log = LoggerFactory.getLogger(getClass());

	@RabbitHandler
	public void doHandle(String message) {
		log.info("消费者A接收到的消息：" + message);
	}
}
