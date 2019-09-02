package com.yjy.user.domain;

import java.sql.Date;

public class User {
	private String username;
	private String password;
	private Date birthday;
	
	public User() {}
	public User(String username, String password, Date birthday) {
		this.username = username;
		this.password = password;
		this.birthday = birthday;
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
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", birthday=" + birthday + "]";
	}
}
