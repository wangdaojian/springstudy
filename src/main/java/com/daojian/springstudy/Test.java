package com.daojian.springstudy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	
	public static void main(String[] args) {
		ApplicationContext act = new ClassPathXmlApplicationContext("application.xml");
		Hello hl = (Hello)act.getBean("hello");
		System.out.println(hl);
		hl.hello();
	}
}