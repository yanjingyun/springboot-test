package com.yjy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.yjy.core.db")
public class Application {
	
	/**
	 * 提交测试：http://localhost:8080/test/add?name=TestAA&age=1&amount=12.02&address=MyAddress
	 * 回滚测试：http://localhost:8080/test/add?name=TestAA&age=0&amount=12.02&address=MyAddress
	 */
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
