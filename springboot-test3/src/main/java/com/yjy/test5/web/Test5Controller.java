package com.yjy.test5.web;

import java.sql.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjy.core.result.Result;
import com.yjy.core.result.ResultUtil;
import com.yjy.test5.entity.User;
import com.yjy.test5.vo.UserSaveVo;

/**
 * 测试更新操作    
 * 描述：前端传部分字段到后端，然后后端通过查询数据库最新记录，再通过反射得到数据库记录与前台实体结合后的实体，jpa直接保存该实体即可
 */
@RestController
@RequestMapping("/test5")
public class Test5Controller {

	// 测试1：最新数据的更新操作
	@RequestMapping("/index1")
	public Result<User> index1() {
		UserSaveVo vo = getVo1(); // 假装从前端得到的vo实体
		User user = vo.getEntity(); // 结合实体
		// userService.save(user); // 模拟保存user，并返回实体
		return ResultUtil.success(user);
	}

	// 测试2：非最新数据的更新操作
	@RequestMapping("/index2")
	public Result<User> index2() {
		UserSaveVo vo = getVo2(); // 假装从前端得到的vo实体
		User user = vo.getEntity(); // 结合实体
		// userService.save(user); // 模拟保存user，并返回实体
		return ResultUtil.success(user);
	}
	

	private static UserSaveVo getVo1() {
		UserSaveVo vo = new UserSaveVo();
		vo.setId(1);
		vo.setVersionNumber(1); // 最新版本号
		vo.setUsername("testAA");
		vo.setBirthday(Date.valueOf("2018-03-06"));
		return vo;
	}

	private static UserSaveVo getVo2() {
		UserSaveVo vo = new UserSaveVo();
		vo.setId(1);
		vo.setVersionNumber(0); // 非最新版本号
		vo.setUsername("testAA");
		vo.setBirthday(Date.valueOf("2018-03-06"));
		return vo;
	}
}
