package com.yjy.core.exception;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.yjy.core.result.Result;
import com.yjy.core.result.ResultEnum;
import com.yjy.core.result.ResultUtil;

@ControllerAdvice
public class ExceptionHandle {

	private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

	// ValidExcepion校验异常
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseBody
	public Result<?> handle(MethodArgumentNotValidException e) {
		List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
		StringBuilder stringBuilder = new StringBuilder();
		for (ObjectError objectError : allErrors) {
			stringBuilder.append(objectError.getObjectName() + "：" + objectError.getDefaultMessage() + "；");
		}
		return ResultUtil.error(ResultEnum.ERROR_TEST3.getCode(),
				stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString());
	}

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public Result<?> handle(Exception e) {
		if (e instanceof MyException) {
			MyException userException = (MyException) e;
			return ResultUtil.error(userException.getCode(), userException.getMessage());
		} else if (e instanceof NoHandlerFoundException) {
			logger.error("【找不到系统资源】{}", e);
			return ResultUtil.error(404, "找不到系统资源");
		} else {
			logger.error("【系统异常】{}", e);
			return ResultUtil.error(-1, "未知错误");
		}
	}
}
