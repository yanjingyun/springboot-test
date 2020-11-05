package com.yjy.rabbitmq.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@GetMapping
	public String send(String msg) {
		Map<String, Object> map = new HashMap<>();
		map.put("msg", msg);
		
		/**
		 * 参数1：队列名称
		 * 参数2：消息
		 * 参数3：消息处理器
		 */
		rabbitTemplate.convertAndSend("delayQueue", map, (message) -> {
			// 设置消息的过期时间，10s
			message.getMessageProperties().setExpiration("10000");
			return message;
		});
		
		System.out.println("发送消息的时间：" + LocalDateTime.now());
		return "success";
	}
}
