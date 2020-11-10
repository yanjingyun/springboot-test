package com.yjy.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义事件：需要继承ApplicationEvent
 */
public class MyEvent extends ApplicationEvent {

    private String name;

    public MyEvent(Object source, String name) {
        super(source);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
