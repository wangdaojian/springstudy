package com.daojian.springstudy;

public class Hello {
	
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void hello() {
		System.out.println("hello: " + name);
	}
}
