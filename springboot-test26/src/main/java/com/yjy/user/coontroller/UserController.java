package com.yjy.user.coontroller;

import com.yjy.user.coontroller.request.UserRequest;
import com.yjy.user.entity.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "user", tags = "用户数据接口")
@RequestMapping("/user")
public class UserController {

	@ApiOperation(value = "查询用户", notes = "根据用户id查询用户")
	@ApiImplicitParam(name = "id", value = "用户id", required = true, defaultValue = "99")
	@GetMapping("/{id}")
	public User getUserById(@PathVariable("id") Integer id) {
		User user = new User();
		user.setId(id);
		return user;
	}

	@ApiOperation(value = "添加用户", notes = "添加用户接口")
	@PostMapping("/")
	public UserRequest addUser(@ApiParam(value = "用户类", required = true) @RequestBody UserRequest userRequest) {
		return userRequest;
	}

	@ApiOperation(value = "更新用户", notes = "根据用户id更新用户名")
	@ApiImplicitParams({ @ApiImplicitParam(name = "id", value = "用户id", required = true, defaultValue = "1"),
			@ApiImplicitParam(name = "username", value = "用户名", required = true) })
	@PutMapping("/")
	public User updateUsernameById(String username, Integer id) {
		User user = new User();
		user.setId(id);
		user.setUsername(username + "《《新的用户名");
		return user;
	}

	@ApiOperation(value = "更新用户v2", notes = "更新用户信息v2")
	@PutMapping("/v2")
	public UserRequest updateUsernameById2(@ApiParam(name = "user", value = "用户类", required = true, defaultValue = "1") UserRequest userRequest) {
		userRequest.setUsername(userRequest.getUsername() + "《《新的用户名");
		return userRequest;
	}

	@ApiOperation(value = "删除用户", notes = "根据用户id删除用户")
	@ApiImplicitParam(name = "id", value = "用户id", required = true)
	@DeleteMapping("/{id}")
	public String deleteUserById(@PathVariable Integer id) {
		return "删除用户id=" + id;
	}

}
