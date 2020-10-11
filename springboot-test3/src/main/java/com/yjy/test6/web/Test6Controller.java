package com.yjy.test6.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yjy.core.result.Result;
import com.yjy.core.result.ResultUtil;
import com.yjy.test6.config.RandomConfig;

/**
 * 测试配置类注入
 */
@RestController
@RequestMapping("/test6")
public class Test6Controller {

	@Autowired
	private RandomConfig randomConfig;
	
	@RequestMapping("")
	public Result<RandomConfig> getRandomConfig() {
		return ResultUtil.success(randomConfig);
	}
}
