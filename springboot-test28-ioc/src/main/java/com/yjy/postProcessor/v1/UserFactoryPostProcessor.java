package com.yjy.postProcessor.v1;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;

public class UserFactoryPostProcessor implements BeanFactoryPostProcessor, Ordered {
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("调用UserFactoryPostProcessor的postProcessBeanFactory方法");
        BeanDefinition beanDefinition = configurableListableBeanFactory.getBeanDefinition("userService");
        MutablePropertyValues pv = beanDefinition.getPropertyValues();
        if (pv.contains("name")) {
            pv.addPropertyValue("name", "TestBB11");
        }
        beanDefinition.setScope(BeanDefinition.SCOPE_PROTOTYPE);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}