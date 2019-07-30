package com.yjy.core.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.yjy.core.result.Result;
import com.yjy.core.result.ResultUtil;

@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result<?> handle(Exception e) {
        if (e instanceof MyException) {
        	MyException userException = (MyException) e;
            return ResultUtil.error(userException.getCode(), userException.getMessage());
        } else if (e instanceof NoHandlerFoundException) {
        	logger.error("【找不到系统资源】{}", e);
        	return ResultUtil.error(404, "找不到系统资源");
        }else {
            logger.error("【系统异常】{}", e);
            return ResultUtil.error(-1, "未知错误");
        }
    }
}
