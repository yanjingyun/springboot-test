package com.yjy.test1.user.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.yjy.core.domain.AuditEntity;

@Entity
@Table(name = "t_user")
public class User extends AuditEntity {

	private static final long serialVersionUID = 3769006097308838106L;
	
	private String name;
	private Date birthday;
	
	public User() {}
	public User(String name, Date birthday) {
		this.name = name;
		this.birthday = birthday;
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
	
	@Override
	public String toString() {
		return "User [name=" + name + ", birthday=" + birthday + "]";
	}
}
