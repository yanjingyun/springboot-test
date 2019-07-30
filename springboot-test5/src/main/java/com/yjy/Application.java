package com.yjy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.yjy.core.dao.BaseDaoFactoryBean;

@SpringBootApplication
// 步骤五：将BaseRepositoryFactoryBean注入
@EnableJpaRepositories(repositoryFactoryBeanClass = BaseDaoFactoryBean.class)
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
