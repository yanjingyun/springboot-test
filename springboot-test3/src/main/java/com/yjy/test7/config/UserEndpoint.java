package com.yjy.test7.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

/**
 * 自定义actuator端点：查看当前登录用户信息的端点
 */
@Component
@Endpoint(id = "user")
public class UserEndpoint {

	@ReadOperation
	public List<Map<String, Object>> health() {
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("userId", 1001);
		map.put("userName", "zhangsan");
		list.add(map);
		return list;
	}
}
