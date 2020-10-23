package com.yjy.user.entity;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = -1509607265177374447L;

	private Long id;

	private String username;

	private Integer age;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}
