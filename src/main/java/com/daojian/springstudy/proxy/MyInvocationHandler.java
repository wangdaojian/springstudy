package com.daojian.springstudy.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class MyInvocationHandler implements InvocationHandler {
	
	private Object object;
	
	public MyInvocationHandler(Object object){
		this.object = object;
	}


	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println(proxy);
		String methodName = method.getName();
		System.out.println("before method " + methodName + ", args: " + Arrays.asList(args));
		//通过反射调用 被代理类的方法
		Object result = method.invoke(object, args);
		System.out.println("result = " + result);
		return result;
	}
	
	
	public static void main(String[] args) {
		Student s = new Student("daojian");
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Class<?>[] interfaces = s.getClass().getInterfaces();
		MyInvocationHandler h = new MyInvocationHandler(s);
		Person person = (Person)Proxy.newProxyInstance(loader, interfaces, h);
		person.giveMoney("30");
		
	}
}
