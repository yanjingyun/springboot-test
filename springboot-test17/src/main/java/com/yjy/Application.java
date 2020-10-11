package com.yjy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	// 新增数据 http://localhost:8080/user/add
	// 查询数据 http://localhost:8080/user/list
	// 查看监控数据源和SQL统计等： http://localhost:8080/druid/index.html
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
