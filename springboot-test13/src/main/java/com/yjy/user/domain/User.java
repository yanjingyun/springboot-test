package com.yjy.user.domain;

import java.sql.Date;

public class User {
	
	public User() {}
	public User(String name, Date birthday) {
		this.name = name;
		this.birthday = birthday;
	}
	
	private String name;
	private Date birthday;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Override
	public String toString() {
		return "User [name=" + name + ", birthday=" + birthday + "]";
	}
}
