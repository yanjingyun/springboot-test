package com.yjy.test2.one2many.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yjy.core.domain.AuditEntity;

@Entity(name="t_customer")
public class Customer extends AuditEntity {

	private static final long serialVersionUID = -861230648168451333L;

	private String name;
	private Date birthday;
	
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy="customer")
	private List<Order> orders = new ArrayList<Order>();

	public Customer() {}
	public Customer(String name, Date birthday) {
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

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
}
