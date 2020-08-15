package com.yjy.test3.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjy.core.result.Result;
import com.yjy.core.result.ResultUtil;
import com.yjy.test3.entity.Role;
import com.yjy.test3.entity.User;
import com.yjy.test3.vo.UserRoleVo;

/**
 * 测试返回多个参数
 */
@RestController
@RequestMapping("/test3")
public class UserRoleController {

	// 测试1：直接返回entity实体
	@GetMapping("/index1")
	public Result<Map<String, Object>> index() {
		User user = new User(1, "userName", "userPassword");
		Role role = new Role(3, "roleName");
		Map<String, Object> map = new HashMap<>();
		map.put("user", user);
		map.put("role", role);
		return ResultUtil.success(map);
	}

	// 测试2：返回vo实体（entity实体需转换成vo实体，如user的password字段在userVo是没有的）
	@GetMapping("/index2")
	public Result<UserRoleVo> index2() {
		// 假设通过service层返回entity实体
		User user = new User(22, "userName", "userPassword");
		Role role = new Role(23, "roleName");
		return ResultUtil.success(UserRoleVo.convertResultData(user, role)); // 封装成vo实体
	}
}
