package com.yjy.v1;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        // 方式1：xml形式
        ApplicationContext context = new FileSystemXmlApplicationContext("/src/main/java/com/yjy/v1/applicationContext.xml");
        Date now = (Date) context.getBean("myFactoryBean");
        System.out.println(now);
        // 这里获取的是FactoryBean
        MyFactoryBean factoryBean = (MyFactoryBean) context.getBean("&myFactoryBean");
        factoryBean.sayName();

        // 方式2：配置类形式
//        ApplicationContext beanFactory = new AnnotationConfigApplicationContext(SpringConfig.class);
//        Date now = (Date) beanFactory.getBean("myFactoryBean");
//        System.out.println(now);
//        // 这里获取的是FactoryBean对象
//        MyFactoryBean factoryBean = (MyFactoryBean) beanFactory.getBean("&myFactoryBean");
//        factoryBean.sayName();
    }
}
