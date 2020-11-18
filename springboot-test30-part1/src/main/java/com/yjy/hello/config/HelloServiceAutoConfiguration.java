package com.yjy.hello.config;

import com.yjy.hello.bean.HelloServiceProperties;
import com.yjy.hello.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义自动配置加载类
 * 1.编写属性配置类，读取application.yml文件初始化属性
 * 2.编写service类
 * 3.编写自定义自动加载配置类
 * 4.在配置类中根据HelloServiceProperties提供的参数，并通过ConditionalOnMissingBean判断容器中没有bean时，注入HelloService
 */
@Configuration
@EnableConfigurationProperties(HelloServiceProperties.class)
@ConditionalOnClass(HelloService.class)
@ConditionalOnProperty(prefix = "hello", value = "enabled", matchIfMissing = true)
public class HelloServiceAutoConfiguration {

    @Autowired
    private HelloServiceProperties helloServiceProperties;

    // 容器中没有指定的HelloService实例时进行初始化
    @Bean
    @ConditionalOnMissingBean(HelloService.class)
    public HelloService helloService() {
        HelloService helloService = new HelloService();
        helloService.setMsg(this.helloServiceProperties.getMsg());
        return helloService;
    }
}
