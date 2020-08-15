package com.yjy.user.domain;

import java.sql.Date;

import com.yjy.core.domain.AudutEntity;

public class User extends AudutEntity {

	private static final long serialVersionUID = -6279553741280812889L;

	private String username;
	
	private String password;
	
	private Date birthday;

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
		super.toString();
		return "User [username=" + username + ", password=" + password + ", birthday=" + birthday + "]";
	}
}
