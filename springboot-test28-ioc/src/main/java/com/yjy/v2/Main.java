package com.yjy.v2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        // 方式1：xml形式
//        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:com/yjy/v2/applicationContext.xml"); // 报错，target文件夹里面不存在这个文件
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:v2/applicationContext.xml");
//        ApplicationContext context = new FileSystemXmlApplicationContext("/src/main/java/com/yjy/v2/applicationContext.xml");
        Student student = (Student) context.getBean("student");
        student.addStudent("user1");
        System.out.println("*********************");
        student.addStudent("user2");
    }
}
