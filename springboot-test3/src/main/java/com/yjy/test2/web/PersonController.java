package com.yjy.test2.web;

import java.util.UUID;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjy.core.result.Result;
import com.yjy.core.result.ResultUtil;
import com.yjy.test2.entity.Person;

@RestController
@RequestMapping("/test2")
public class PersonController {

	@RequestMapping("/validate")
	public Person validatePerson(@Validated @RequestBody Person person) {
		person.setId(UUID.randomUUID().toString());
		return person;
	}
	
	@RequestMapping("/validate2")
	public Result<Person> validatePerson2(@Validated @RequestBody Person person) {
		person.setId(UUID.randomUUID().toString());
		return ResultUtil.success(person);
	}
}
