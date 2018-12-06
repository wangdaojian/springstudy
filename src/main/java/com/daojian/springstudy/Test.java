package com.daojian.springstudy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	
	public static void main(String[] args) {
		ApplicationContext act = new ClassPathXmlApplicationContext("application.xml");
		/*Hello hl = act.getBean(Hello.class);
		System.out.println(hl);
		hl.hello();*/
		/*Car car = (Car)act.getBean("car");
		System.out.println(car);*/
		
		
		/*Person per = (Person)act.getBean("person");
		Person per2 = (Person)act.getBean("person");
		
		System.out.println(per == per2);*/
		
		DataSource ds = (DataSource) act.getBean("dataSource");
		System.out.println(ds);
	}
}
