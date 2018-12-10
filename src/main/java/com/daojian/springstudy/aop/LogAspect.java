package com.daojian.springstudy.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
	
	@Before("execution(public int com.daojian.springstudy.aop.ArithmeticCalculatorImpl.*(int,int))")
	public void beforeMethod(JoinPoint jp) {
		System.out.println("before method " + jp.getSignature().getName() + ", args=" + Arrays.asList(jp.getArgs()));
	}
	
	@After("execution(public int com.daojian.springstudy.aop.ArithmeticCalculatorImpl.*(int,int))")
	public void afterMethod(JoinPoint jp) {
		System.out.println("after method " + jp.getSignature().getName() + " end");
	}
	
	@AfterReturning(value= "execution(public int com.daojian.springstudy.aop.ArithmeticCalculatorImpl.*(int,int))",
			returning="result")
	public void afterReturnMethod(JoinPoint jp, Object result) {
		System.out.println("afterReturnMethod method " + jp.getSignature().getName() + " end, result = " + result);
	}
	

	@Around(value= "execution(public int com.daojian.springstudy.aop.ArithmeticCalculatorImpl.*(int,int))")
	public Object aroundMethod(ProceedingJoinPoint pjd) {
		System.out.println("aroundMethod method " + pjd.getSignature().getName() + "....");
		Object result = null;
		try {
			result = pjd.proceed(pjd.getArgs());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("aroundMethod method end, result = " +  result);
		return result;
	}
}
