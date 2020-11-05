package com.yjy.rabbitmq.listener;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "my-dlx-queue")
public class MessageListener {

	@RabbitHandler
	public void receive(Map<String, Object> map) {
		System.out.println("接收消息的时间：" + LocalDateTime.now());
		System.out.println("从MQ中接收到的消息：" + map.get("msg"));
	}
}
