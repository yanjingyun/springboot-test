package com.yjy.core.exception;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.yjy.core.result.Result;
import com.yjy.core.result.ResultEnum;
import com.yjy.core.result.ResultUtil;

@ControllerAdvice
public class ExceptionHandle {

	private final static Logger log = LoggerFactory.getLogger(ExceptionHandle.class);
	
	@ExceptionHandler(value = MyException.class)
	@ResponseBody
	public Result<?> handle(MyException e) {
		log.error("【自定义异常】{}", e);
		return ResultUtil.error(e.getCode(), e.getMessage());
	}
	
	@ExceptionHandler(value = NoHandlerFoundException.class)
	@ResponseBody
	public Result<?> handle(NoHandlerFoundException e) {
		log.error("【找不到系统资源】{}", e);
		return ResultUtil.error(404, "找不到系统资源1");
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseBody
    public Result<?> mismatchErrorHandler(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        log.error("【找不到系统资源】 方法:{},字段:{},参数:{},错误信息:{}", e.getParameter().getMethod(), e.getName(), e.getValue(), e.getMessage());
        return ResultUtil.error(404, "找不到系统资源2");
    }
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseBody
	public Result<?> handle(MethodArgumentNotValidException e) {
		log.error("【校验异常】{}", e);
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
		log.error("【系统异常】{}", e);
		return ResultUtil.error(-1, "未知错误");
	}
}
