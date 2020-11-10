package com.yjy.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 自定义事件监听器
 */
@Component
public class MyEventListener {

    private static final Logger logger = LoggerFactory.getLogger(MyEventListener.class);

    @EventListener // 注意此处
    public void handleDemoEvent(MyEvent event) {
        logger.info("发布内容为:{}", event.getName());
    }
}