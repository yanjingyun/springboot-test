package com.yjy.core.utils;

public class CodeName {
	
	public static final String PRE_TEST_CODE = "测试调用静态变量"; 
	
	//模拟字典表返回
	public static String getName(String type, String code) {
		System.out.println("type=" + type + ", code=" + code);
		return type+":"+code;
	}
}
