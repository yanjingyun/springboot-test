package com.yjy.rabbitmq.controller;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjy.rabbitmq.config.RabbitConfig;

@RestController
public class RabbitmqController {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	private AtomicInteger count = new AtomicInteger();

	@GetMapping("send")
	public void sendMessage() {
		String message = "生产者生产消息" + count.incrementAndGet();
		rabbitTemplate.convertAndSend(RabbitConfig.exchangeName, null, message);
	}
}
