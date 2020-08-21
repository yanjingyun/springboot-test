package com.yjy.core.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 捕获报错日志处理工具类
 */
public class ErrorUtil {
	/**
     * Exception出错的栈信息转成字符串，用于打印到日志中
     * 描述：考虑将exception内容存到数据库，就需要将其转换为字符串了
     */
    public static String getStackTraceAsString(Exception e) {
		StringWriter stringWriter = new StringWriter();
		e.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.toString();
	}
}
