package com.yjy.test1.user.vo;

import java.sql.Date;

import com.yjy.core.annotation.Filter;
import com.yjy.core.annotation.Filter.OperatorEnum;
import com.yjy.core.vo.query.AbstractQueryVo;
import com.yjy.test1.user.domain.User;

public class UserQueryVo extends AbstractQueryVo<User> {

	private static final long serialVersionUID = 3537083039120463058L;

	@Filter(operator = OperatorEnum.LIKE_ALL)
	private String name;
	
	@Filter(fieldName = "birthday", operator = OperatorEnum.GREATER_THAN_OR_EQUAL)
	private Date birthdayBegin;

	@Filter(fieldName = "birthday", operator = OperatorEnum.LESS_THAN_OR_EQUAL)
	private Date birthdayEnd;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthdayBegin() {
		return birthdayBegin;
	}
	public void setBirthdayBegin(Date birthdayBegin) {
		this.birthdayBegin = birthdayBegin;
	}
	public Date getBirthdayEnd() {
		return birthdayEnd;
	}
	public void setBirthdayEnd(Date birthdayEnd) {
		this.birthdayEnd = birthdayEnd;
	}
}
