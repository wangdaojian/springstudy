package com.daojian.springstudy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {

	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("beanpostProcessor: before" +  bean + ", " + beanName);
		if(beanName.equals("car")) {
			Car car = (Car)bean;
			car.setName("奥迪");
			bean = car;
		}
		return bean;
	}

	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("beanpostProcessor: after" +  bean + ", " + beanName);
		return bean;
	}

}
