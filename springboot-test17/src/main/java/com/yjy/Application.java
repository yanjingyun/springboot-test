package com.yjy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan //扫描servlet、filter
public class Application {

	// 存在问题：为什么不是配置的servlet、filter无效
	// 新增数据 http://localhost:8080/user/add
	// 查询数据 http://localhost:8080/user/list
	// 查看监控数据源和SQL统计等： http://localhost:8080/druid/index.html
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
