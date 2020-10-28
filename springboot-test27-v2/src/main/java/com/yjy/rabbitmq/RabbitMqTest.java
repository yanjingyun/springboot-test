package com.yjy.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqTest {

	@Autowired
	private AmqpTemplate amqpTemplate;

	/** 简单队列模式-生产者 */
	@Test
	public void simpleTest() {
		String msg = "这是一个简单队列模式";
		amqpTemplate.convertAndSend("spring.simple.queue", msg);
	}

	/** work模式-生产者 */
	@Test
	public void work() throws InterruptedException {
		String msg = "这是一个work模式";
		for (int i = 0; i < 10; i++) {
			amqpTemplate.convertAndSend("spring.work.queue", msg + i);
		}
		Thread.sleep(5000);
	}

	/** 订阅模式-生产者 */
	@Test
	public void fanout() throws InterruptedException {
		String msg = "订阅模式";
		for (int i = 0; i < 10; i++) {
			// 这里注意细节，第二个参数需要写，否则第一个参数就变成routingKey了
			amqpTemplate.convertAndSend("spring.fanout.exchange", "", msg + i);
		}
		Thread.sleep(5000);
	}

	/** 路由模式(Direct)-生产者 */
	@Test
	public void direct() throws InterruptedException {
		String msg = "路由模式";
		for (int i = 1; i <= 5; i++) {
			amqpTemplate.convertAndSend("spring.direct.exchange", "direct-key1", msg + i);
		}
		for (int i = 6; i <= 10; i++) {
			amqpTemplate.convertAndSend("spring.direct.exchange", "direct-key2", msg + i);
		}
		Thread.sleep(5000);
	}

	/** 主题模式（topic）-消费者 */
	@Test
	public void topic() throws InterruptedException {
		amqpTemplate.convertAndSend("spring.topic.exchange", "person.insert", "增加人员");
		amqpTemplate.convertAndSend("spring.topic.exchange", "person.delete", "删除人员");
		amqpTemplate.convertAndSend("spring.topic.exchange", "money.insert", "加钱");
		amqpTemplate.convertAndSend("spring.topic.exchange", "money.delete", "减钱");
		Thread.sleep(5000);
	}
}
