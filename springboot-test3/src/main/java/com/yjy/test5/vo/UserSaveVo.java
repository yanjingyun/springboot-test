package com.yjy.test5.vo;

import java.sql.Date;

import com.yjy.core.vo.SaveVo;
import com.yjy.test5.entity.User;

public class UserSaveVo extends SaveVo<User> {

	private String username;

	private Date birthday;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
}
