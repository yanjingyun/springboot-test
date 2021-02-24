package com.yjy.v1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    MyFactoryBean myFactoryBean() {
        return new MyFactoryBean();
    }
}
