package com.yjy.hello.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 属性加载配置类
 * 通过加载配置文件初始化属性值
 */
@ConfigurationProperties(prefix = "hello")
public class HelloServiceProperties {

    private final String DEFAULT_MSG = "default_msg default_msg default_msg";
    private String msg = DEFAULT_MSG;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
