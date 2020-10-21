package com.yjy.test6.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
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
	
	@Autowired
	private Environment env;
	
	@Value("${my.name}")
	private String myName;
	
	// 方式1：Environment形式
	@RequestMapping("/env")
	public String testEnv() {
		String myName = env.getProperty("my.name");
		return myName;
	}
	
	// 方式2：@Value形式
	@RequestMapping("/value")
	public String testValue() {
		return myName;
	}
	
	// 方式3：@ConfigurationProperties形式
	@RequestMapping("/config")
	public Result<RandomConfig> getRandomConfig() {
		return ResultUtil.success(randomConfig);
	}
}
