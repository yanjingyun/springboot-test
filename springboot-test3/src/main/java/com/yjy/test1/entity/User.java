package com.yjy.test1.entity;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = 4606395505520873721L;
	
	private Integer id;
	private String name;
	
	public User() {}
	public User(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
