package com.daojian.springstudy.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
	
	@Before("execution(public int com.daojian.springstudy.aop.ArithmeticCalculatorImpl.add(int,int))")
	public void beforeMethod(JoinPoint jp) {
		System.out.println(jp.toString());
		System.out.println(jp.toShortString());
		System.out.println(jp.toLongString());
		System.out.println(jp.getThis());
		System.out.println(jp.getTarget());
		System.out.println("before method " + jp.getSignature().getName() + ", args=" + Arrays.asList(jp.getArgs()));
	}
}
