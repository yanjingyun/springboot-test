package com.yjy.postProcessor.v2;

import org.springframework.beans.factory.InitializingBean;

public class Person implements InitializingBean {
    private String name;
    private int age;

    public Person() {
        System.out.println("Person的无参构造函数");
    }

    public Person(String name, int age) {
        System.out.println("Person的有参构造函数，正在创建Person对象");
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("调用afterPropertiesSet方法，此时用来修改name属性值");
        this.name = "acorn";
    }

    public void init() {
        System.out.println("调用init方法");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}