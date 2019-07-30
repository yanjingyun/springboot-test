package com.yjy.core.result;

public enum ResultEnum {
	UNKONW_ERROR(-1, "未知错误"),
    SUCCESS(0, "成功"),
    ERROR_TEST1(101, "错误1..."),
    ERROR_TEST2(102, "错误2..."),
    ;

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}
