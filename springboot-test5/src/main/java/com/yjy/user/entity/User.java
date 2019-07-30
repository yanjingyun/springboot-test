package com.yjy.user.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.yjy.core.entity.IdEntity;

@Entity
@Table(name = "t_user")
public class User extends IdEntity {

	private static final long serialVersionUID = -8408687461150810740L;

	private String name;
	private Integer age;
	
	public User() {}
	public User(String name, Integer age) {
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + "]";
	}
}
