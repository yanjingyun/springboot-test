package com.yjy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
	
	// 测试：http://localhost:8080/test?msg=testAA
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
