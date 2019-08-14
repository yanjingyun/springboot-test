package com.yjy.test1.user.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjy.test1.user.domain.User;
import com.yjy.test1.user.service.UserService;
import com.yjy.test1.user.vo.UserQueryVo;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("list")
	public List<User> getList(UserQueryVo vo) {
		return userService.getList(vo);
	}

	/**
	 * 传参：name=3&birthdayBegin=1995-05-02&birthdayEnd=1995-05-04
	 * 输出：一条记录
	 */
	@RequestMapping("list2")
	public List<User> getList2(UserQueryVo vo) {
		return userService.getList2(vo);
	}

	/**
	 * 传参：name=test&pageNumber=1
	 * 输出：对应页数据
	 */
	@RequestMapping("page")
	public Page<User> getPage(UserQueryVo vo) {
		return userService.getPage(vo);
	}
}
