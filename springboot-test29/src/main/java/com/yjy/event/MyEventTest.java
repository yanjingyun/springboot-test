package com.yjy.event;

import com.yjy.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试：事件发布操作，有了事件和监听器，就可以将事件发布出去
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyEventTest implements ApplicationContextAware {
    private static Logger logger = LoggerFactory.getLogger(MyEventTest.class);

    private ApplicationContext context = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Test
    public void listener() {
        // 自定义事件发布
        context.publishEvent(new MyEvent(this, "测试"));
    }
}
