package com.yjy.utils;

/**
 * 定义Controller层常用属性的方法
 */
public class Controllers {
	/* 消息名 */
	public static final String DEFAULT_MESSGAE_NAME = "message";

	/* 常用提示语 */
	public static final String DELETED_SUCCESSFULLY = "删除成功";
	
	public static final String SAVED_SUCCESSFULLY = "保存成功";
	
	public static final String SUBMITED_SUCCESSFULLY = "提交成功";

	public static final String OPERATE_SUCCESSFULLY = "操作成功";
	
	public static final String CANCELED_SUCCESSFULLY = "取消成功";
	
	/**
	 * 弹出提示，并执行指定js方法
	 */
	private static String renderScript(String message, String functionName) {
		String renderScript = "<head>"
				+ "<script type='text/javascript' src='/js/jquery-3.1.1.min.js'></script>"
				+ "<script type='text/javascript' src='/js/common-util.js'></script>"
				+ "<script type='text/javascript'>"
				+ "$(function() {" + functionName + "('"+message+"')" + "})"
				+ "</script>"
				+ "</head>";
		return renderScript;
	}
	
	public static String closeAndAlert(String message) {
		return renderScript(message, "closeAndAlert");
	}
	
	public static String closeAndRefreshParentsPage(String message) {
		return renderScript(message, "closeAndRefreshParentsPage");
	}
}
