package com.yjy.test1.user.vo;

import com.yjy.core.vo.save.AbstractSaveVo;
import com.yjy.test1.user.domain.User;

public class UserSaveVo extends AbstractSaveVo<User> {

	private static final long serialVersionUID = -8908959615838291289L;

	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
