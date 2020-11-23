package com.yjy.order.vo;

import com.yjy.order.entity.Order;

public class OrderVo extends Order {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
