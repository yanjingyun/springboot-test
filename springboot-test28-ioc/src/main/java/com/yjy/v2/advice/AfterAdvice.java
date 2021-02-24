package com.yjy.v2.advice;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class AfterAdvice implements AfterReturningAdvice {
    @Override
    public void afterReturning(Object arg0, Method arg1, Object[] arg2, Object arg3) throws Throwable {
        System.out.println("这个是 AfterReturning 方法!");
    }
}