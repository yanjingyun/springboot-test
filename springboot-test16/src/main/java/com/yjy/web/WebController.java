package com.yjy.web;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yjy.domain.User;
import com.yjy.utils.Controllers;

@Controller
public class WebController {

	@RequestMapping("/list")
	public String list(Model model) {
		Random randmon = new Random();
		int size = randmon.nextInt(10) + 3;
		List<User> users = new ArrayList<>();
		for(int i = 1; i < size; i++) {
			users.add(new User("testAA" + i, Date.valueOf("1995-05-"+i)));
		}
		model.addAttribute("users", users);
		return "/list";
	}
	
	@RequestMapping("/form")
	public String form(String name, Model model) {
		model.addAttribute("user", new User(name, Date.valueOf("1995-05-10")));
		return "form";
	}

	@RequestMapping("/form/save-and-close")
	@ResponseBody
	public String saveAndClose(User user) {
		System.out.println(user);
		return Controllers.closeAndAlert(Controllers.SAVED_SUCCESSFULLY);
	}

	@RequestMapping("/form/save-and-refresh")
	@ResponseBody
	public String saveAndRefreshPage(User user) {
		System.out.println(user);
		return Controllers.closeAndRefreshParentsPage(Controllers.SAVED_SUCCESSFULLY);
	}
}
