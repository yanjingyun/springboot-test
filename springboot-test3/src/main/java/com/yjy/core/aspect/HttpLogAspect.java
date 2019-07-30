package com.yjy.core.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 日志输出
 */
@Aspect
@Component
public class HttpLogAspect {
    private final static Logger logger = LoggerFactory.getLogger(HttpLogAspect.class);

    @Pointcut("execution(public * com.yjy.*.controller.*.*(..))")
    public void log() { }

    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
    }

    @After("log()")
    public void doAfter() {
        logger.info("doAfter.......");
    }
    
    @AfterReturning(returning = "returnValue", pointcut = "log()")
    public void doAfterReturning(JoinPoint joinPoint, Object returnValue) throws JsonProcessingException {
    	ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        ObjectMapper mapper = new ObjectMapper();
        
        logger.info("doAfterReturning...");
        logger.info("模拟日志记录功能...");
        logger.info("url={}", request.getRequestURL());
        logger.info("ip地址={}", request.getRemoteAddr());
        logger.info("方法名class_method={}", joinPoint.getSignature());
        logger.info("参数agrs={}", mapper.writeValueAsString(joinPoint.getArgs()));
        logger.info("返回值reponse={}", mapper.writeValueAsString(returnValue));
        logger.info("被织入的目标对象obj={}", joinPoint.getTarget());
    }
    
    @AfterThrowing(throwing = "ex", pointcut = "log()")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable ex) {
    	logger.error("doAfterThrowing...");
    	logger.error("exception={}", ex);
    }
}