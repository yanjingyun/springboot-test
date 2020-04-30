package com.yjy.test.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.yjy.core.exception.MyException;
import com.yjy.core.result.Result;
import com.yjy.core.result.ResultEnum;
import com.yjy.core.result.ResultUtil;
import com.yjy.test.entity.User;

@RestController
public class UserController {

	// 测试日志：查询所有记录
	@GetMapping(value = "/users")
	public List<User> getUsers(String name) {
		List<User> list = new ArrayList<>();
		list.add(new User(1, "testAA1"));
		list.add(new User(2, "testAA2"));
		list.add(new User(5, name));
		return list;
	}

	// 测试日志：查询一条记录
	@GetMapping(value = "/users/{id}")
	public User findOne(@PathVariable("id") Integer id) {
		User user = new User(id, "testAA");
		return user;
	}

	// 统一数据格式:查询所有记录
	@GetMapping(value = "/data")
	public Result<List<User>> testData(String name) {
		List<User> list = new ArrayList<>();
		list.add(new User(1, "testAA1"));
		list.add(new User(2, "testAA2"));
		list.add(new User(5, name));
		return ResultUtil.success(list);
	}

	// 统一数据格式:查询一条记录
	@GetMapping(value = "/data/{id}")
	public Result<User> testData2(@PathVariable Integer id, String username) {
		System.out.println(username);
		User user = new User(1, "testAA");
		return ResultUtil.success(user);
	}

	// 统一数据格式:测试系统异常
	@GetMapping(value = "/data/testException")
	public Result<?> testException() {
		int i = 10 / 0; // 抛异常
		System.out.println(i);

		return ResultUtil.success();
	}

	// 统一数据格式:测试自定义抛出异常
	@GetMapping(value = "/data/testException2")
	public Result<?> testException2() {
		throw new MyException(ResultEnum.ERROR_TEST2); // 抛异常
		// return ResultUtil.success();
	}

	// 统一数据格式:测试自定义异常
	@GetMapping(value = "/data/testException3")
	public Result<?> testException3() {
		return ResultUtil.error(ResultEnum.ERROR_TEST1.getCode(), ResultEnum.ERROR_TEST1.getMsg());
	}

	// 统一数据格式:测试自定义异常
	@GetMapping(value = "/data/testException4")
	public Result<?> testException4() {
		return ResultUtil.error(ResultEnum.ERROR_TEST2.getCode(), ResultEnum.ERROR_TEST2.getMsg());
	}
}
