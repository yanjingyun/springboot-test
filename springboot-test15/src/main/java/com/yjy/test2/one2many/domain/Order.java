package com.yjy.test2.one2many.domain;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.yjy.core.domain.AuditEntity;

@Entity(name="t_order")
public class Order extends AuditEntity {

	private static final long serialVersionUID = -4183740941905589056L;

	private String name;
	private Date orderDate;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	public Order() {}
	public Order(String name, Date orderDate) {
		this.name = name;
		this.orderDate = orderDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
