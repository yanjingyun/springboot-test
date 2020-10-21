package com.yjy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yjy.*.mapper")
public class Application {
	
	// 测试1：http://localhost:8080/user/findAll
	// 测试2：http://localhost:8080/order/findAll
	// 测试3：http://localhost:8080/order/find/1
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
