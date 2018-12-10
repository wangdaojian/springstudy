package com.daojian.springstudy;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.daojian.springstudy.aop.ArithmeticCalculator;
import com.daojian.springstudy.generic.di.UserService;


public class Test {
	
	public static void main(String[] args) {
		ApplicationContext act = new ClassPathXmlApplicationContext("application.xml");
		/*Hello hl = act.getBean(Hello.class);
		System.out.println(hl);
		hl.hello();*/
		//Car car2 = (Car)act.getBean("car2");
		//System.out.println(car2);
		
		//Car car1 = (Car)act.getBean("car1");
		//System.out.println(car1);
		
		
		/*Car car3 = (Car)act.getBean("car3");
		System.out.println(car3);*/
		
		/*TestObject to = (TestObject) act.getBean("testObject");
		System.out.println(to);*/
		
		/*UserRepository respo = (UserRepository) act.getBean("userRepository");
		System.out.println(respo);
		
		UserService service = (UserService) act.getBean("userService");
		System.out.println(service);*/
		
		/*UserController controller = (UserController) act.getBean("userController");
		System.out.println(controller);
		controller.execute();*/
		
		/*UserService us = (UserService) act.getBean("baseUserService");
		us.add();*/
		
		ArithmeticCalculator ari = (ArithmeticCalculator) act.getBean(ArithmeticCalculator.class);
		ari.div(10, 0);
		
		//act.destroy();
		
		/*Person per = (Person)act.getBean("person");
		Person per2 = (Person)act.getBean("person");
		
		System.out.println(per == per2);*/
		
		/*DataSource ds = (DataSource) act.getBean("dataSource");
		System.out.println(ds);*/
	}
}
