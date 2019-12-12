package com.yjy.scheduled;

import java.sql.Timestamp;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask2 {

	@Scheduled(fixedRate = 1000) //每超过5秒执行一次
	public void task1() {
		System.out.println("Task2当前时间：" + new Timestamp(System.currentTimeMillis()) + " " + Thread.currentThread().getName() + "执行任务1...");
	}

	@Scheduled(fixedRate = 2000) //每超过5秒执行一次
	public void taks2() {
		System.out.println("Task2当前时间：" + new Timestamp(System.currentTimeMillis()) + " " + Thread.currentThread().getName() + "执行任务2...");
	}
}
