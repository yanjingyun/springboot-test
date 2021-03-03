package com.yjy.postProcessor.v2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("postProcessor/v2/applicationContext.xml");

        Person person = (Person) context.getBean("person");
        System.out.println(person);
        /****结果为：
             调用PersonFactoryPostProcessor的postProcessBeanFactory方法
             10:14:43.741 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'personPostProcessor'
             Person的无参构造函数
             BeanPostProcessor，对象person调用初始化方法之前的数据： Person{name='TestAA', age=23}
             调用afterPropertiesSet方法，此时用来修改name属性值
             调用init方法
             BeanPostProcessor，对象person调用初始化方法之后的数据：Person{name='acorn', age=23}
             Person{name='clearLove', age=23}
         */
    }
}
