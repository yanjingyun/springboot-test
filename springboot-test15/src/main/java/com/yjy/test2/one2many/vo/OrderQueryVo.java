package com.yjy.test2.one2many.vo;

import java.sql.Date;

import com.yjy.core.annotation.Filter;
import com.yjy.core.annotation.Filter.OperatorEnum;
import com.yjy.core.vo.query.AbstractQueryVo;
import com.yjy.test2.one2many.domain.Order;

public class OrderQueryVo extends AbstractQueryVo<Order> {

	private static final long serialVersionUID = -4139085186986795031L;

	@Filter(operator = OperatorEnum.LIKE_ALL)
	private String name;
	
	@Filter
	private Date orderDate;
	
	@Filter(fieldName="customer.name", operator = OperatorEnum.EQUAL)
	private String customerName;
	
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
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}
