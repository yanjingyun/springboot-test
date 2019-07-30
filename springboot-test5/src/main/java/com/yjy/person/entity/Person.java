package com.yjy.person.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.yjy.core.entity.IdEntity;

@Entity
@Table(name = "t_person")
public class Person extends IdEntity implements Serializable {

	private static final long serialVersionUID = 6414692783170413368L;

	private String name;
	private Integer age;
	
	public Person() {}
	public Person(String name, Integer age) {
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
		return "Person [name=" + name + ", age=" + age + "]";
	}
}
