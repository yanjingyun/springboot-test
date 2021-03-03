package com.yjy.postProcessor.v2;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class PersonFactoryPostProcessor implements BeanFactoryPostProcessor {
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("调用PersonFactoryPostProcessor的postProcessBeanFactory方法");
        BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition("person");
        MutablePropertyValues pv = beanDefinition.getPropertyValues();
        if (pv.contains("age")){
            pv.addPropertyValue("age","23");
        }
        beanDefinition.setScope(BeanDefinition.SCOPE_PROTOTYPE);
    }
}