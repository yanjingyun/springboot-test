package com.yjy.order.entity;

import java.io.Serializable;

public class Order implements Serializable {

	private static final long serialVersionUID = 627699304799429172L;

	private Long id;

	private String name;

	private Long userId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
