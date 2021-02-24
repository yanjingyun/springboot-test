package com.yjy.v1;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.FactoryBean;

import java.util.Date;

public class MyFactoryBean implements FactoryBean<Date>, BeanNameAware {

    private String beanName;

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public Date getObject() throws Exception {
        return new Date();
    }

    @Override
    public Class<?> getObjectType() {
        return Date.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public void sayName() {
        System.out.println("My name is " + this.beanName);
    }

}
