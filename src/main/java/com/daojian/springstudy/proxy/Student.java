package com.daojian.springstudy.proxy;

public class Student implements Person {
	private String name;

	public Student(String name) {
		this.name = name;
	}

	public void giveMoney(String money) {
		System.out.println(name + "上交班费" + money + "元");
	}
}
