package com.yjy.user.web;

import java.sql.Date;

import com.yjy.user.domain.User;
import com.yjy.user.vo.UserSaveVo;

// 模拟controller层
public class UserController {

	public static void main(String[] args) {
		UserSaveVo vo = getVo(); //模拟从前台传过来的实体
		User user = vo.getEntity(); // 得到数据库记录与前台实体结合后的实体，jpa直接保存该实体即可
		System.out.println(user);
	}

	private static UserSaveVo getVo() {
		UserSaveVo vo = new UserSaveVo();
		vo.setId(1);
		vo.setVersionNumber(2);
		vo.setUsername("testAA");
		vo.setBirthday(Date.valueOf("2018-03-06"));
		return vo;
	}
}
