package com.yjy.postProcessor.v1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("postProcessor/v1/applicationContext.xml");
        UserService userService = (UserService) context.getBean("userService");
        System.out.println(userService);
        System.out.println(userService.getName());
        UserService userService2 = (UserService) context.getBean("userService");
        System.out.println(userService2);
        System.out.println(userService2.getName());
        /**
         * 输出结果：
         对容器进行处理后。。。。。
         09:43:35.904 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'userService'
         com.yjy.beanFactoryPostProcessor.v1.UserService@4d3167f4
         TestAA111
         com.yjy.beanFactoryPostProcessor.v1.UserService@4d3167f4
         TestAA111
         */
    }
}
