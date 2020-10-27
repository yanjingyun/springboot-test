package com.yjy.user.coontroller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yjy.user.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "用户数据接口")
public class UserController {

	@ApiOperation(value = "查询用户", notes = "根据用户id查询用户")
	@ApiImplicitParam(name = "id", value = "用户id", required = true, defaultValue = "99")
	@GetMapping("/user")
	public User getUserById(Integer id) {
		User user = new User();
		user.setId(id);
		return user;
	}

	@ApiOperation(value = "添加用户", notes = "添加用户接口")
	@PostMapping("/user")
	public User addUser(@RequestBody User user) {
		return user;
	}

	@ApiOperation(value = "更新用户", notes = "根据用户id更新用户名")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "用户id", required = true, defaultValue = "99"),
			@ApiImplicitParam(name = "username", value = "用户名", required = true, defaultValue = "javaboy") })
	@PutMapping("/user")
	public User updateUsernameById(String username, Integer id) {
		User user = new User();
		user.setId(id);
		user.setUsername(username);
		return user;
	}

	@ApiOperation(value = "删除用户", notes = "根据用户id删除用户")
	@ApiImplicitParam(name = "id", value = "用户id", required = true)
	@DeleteMapping("/user/{id}")
	public String deleteUserById(@PathVariable Integer id) {
		return "删除用户id" + id;
	}

}
