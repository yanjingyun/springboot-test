package com.yjy.user.entity;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = -1509607265177374447L;

	private Long id;

	private String username;

	private String password;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
