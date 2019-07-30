package com.yjy.user.domain;

import java.io.Serializable;
import java.sql.Date;

public class User implements Serializable {

	private static final long serialVersionUID = -5400964824797998909L;

	private String name;
	private Date birthday;
	private Integer age;
	
	public User() {}
	public User(String name, Date birthday, Integer age) {
		super();
		this.name = name;
		this.birthday = birthday;
		this.age = age;
	}

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
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "User [name=" + name + ", birthday=" + birthday + ", age=" + age + "]";
	}
}
