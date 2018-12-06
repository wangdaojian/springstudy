package com.daojian.springstudy;

public class Car {
	private String name; 
	private String color;
	private int size;
	public Car(String name, String color, int size) {
		super();
		this.name = name;
		this.color = color;
		this.size = size;
	}
	
	
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	@Override
	public String toString() {
		return "Car [name=" + name + ", color=" + color + ", size=" + size + "]";
	}
	
}
