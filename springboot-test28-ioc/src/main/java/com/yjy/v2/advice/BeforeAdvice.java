package com.yjy.v2.advice;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class BeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method arg0, Object[] arg1, Object arg2)
            throws Throwable {
        System.out.println("这是BeforeAdvice的before方法！");
    }
}