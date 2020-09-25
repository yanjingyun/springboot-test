package com.yjy.user.rest;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.yjy.user.domain.User;

public class UserRest {
	
	/**
	 * Get请求
	 * 1、普通http测试（数字占位符传参）
	 */
	@Test
	public void testHttp() {
		RestTemplate restTemplate = new RestTemplate();
		String responseData = restTemplate.getForObject("http://localhost:8080/users/testHttp?username={1}&password={2}", String.class, "testAA", "123");
		System.out.println(responseData);
	}

	/**
	 * Get请求
	 * 1、普通http测试（map传参）
	 */
	@Test
	public void testHttpMap() {
		Map<String, String> map = new HashMap<>();
		map.put("username", "testMap");
		map.put("password", "123");
		
		RestTemplate restTemplate = new RestTemplate();
		
		String responseData = restTemplate.getForObject("http://localhost:8080/users/testHttp?username={username}&password={password}", String.class, map);
		System.out.println(responseData);
	}
	
	
	/**
	 * rest风格的Get请求
	 * 1、Object类型传参，针对请求参数较少
	 */
	@Test
	public void testGet() {
		RestTemplate restTemplate = new RestTemplate();
		Integer id = 2;
		User user = restTemplate.getForObject("http://localhost:8080/users/{id}", User.class, id);
		System.out.println(user);
	}
	
	/**
	 * rest风格的Get请求
	 * 1、Map类型传参，针对请求参数很多
	 */
	@Test
	public void testGetMap() {
		RestTemplate restTemplate = new RestTemplate();
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("id", 22);
		params.put("name", "testBB");
		params.put("birthday", "1995-07-10");
		User user = restTemplate.getForObject("http://localhost:8080/users/{id}/{name}/{birthday}", User.class, params);
		System.out.println(user);
	}
	
	/**
	 * Post请求，模拟新增用户
	 * 1、User类型传参
	 */
	@Test
	public void testPost() {
		// 构造请求头和请求参数
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		User requestUser = new User(1, "testPost", Date.valueOf("1994-02-10"));
		HttpEntity<User> requestData = new HttpEntity<>(requestUser, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		User responseUser = restTemplate.postForObject("http://localhost:8080/users", requestData, User.class);
		System.out.println(responseUser);
	}
	
	/**
	 * Post请求，模拟新增用户
	 * 2、Map传参
	 */
	@Test
	public void testPostMap() {
		// 构造请求头和请求参数
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		Map<String, Object> params = new HashMap<>();
		params.put("name", "testAA");
		params.put("birthday", "1995-06-07");
		@SuppressWarnings("rawtypes")
		HttpEntity<Map> requestData = new HttpEntity<>(params, headers);
		
		RestTemplate restTemplate = new RestTemplate();
		User str = restTemplate.postForObject("http://localhost:8080/users", requestData, User.class);
		System.out.println(str);
	}
	
	private RestTemplate getRestTemplate() {
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setConnectTimeout(1000); // 连接超时时间
		requestFactory.setReadTimeout(1000); // 从服务器读取超时
		return new RestTemplate(requestFactory);
	}
	
	/**
	 * 测试连接超时
	 */
	@Test
	public void testConnectionTimeout() {
		RestTemplate restTemplate = getRestTemplate();
		try {
			String responseData = restTemplate.getForObject("http://localhost:8080/users/testTimeout11", String.class);
			System.out.println(responseData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 测试请求超时
	 */
	@Test
	public void testReadTimeout() {
		RestTemplate restTemplate = getRestTemplate();
		try {
			String responseData = restTemplate.getForObject("http://localhost:8080/users/testTimeout", String.class);
			System.out.println(responseData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
