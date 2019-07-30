package com.yjy.many2many.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity(name="t_role")
public class Role implements Serializable {

	private static final long serialVersionUID = -2762561149112572786L;

	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="roles")
	private List<User> users = new ArrayList<User>();

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

	public List<User> getUsers() {
		return users;
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}
}
