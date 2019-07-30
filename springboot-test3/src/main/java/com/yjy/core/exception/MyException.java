package com.yjy.core.exception;

import com.yjy.core.result.ResultEnum;

public class MyException extends RuntimeException {

	private static final long serialVersionUID = -191426738820214009L;
	
	private Integer code;
	
	public MyException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }
	
	public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
}
