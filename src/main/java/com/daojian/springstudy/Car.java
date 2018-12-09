package com.daojian.springstudy;

public class Car {
	private String name; 
	private int size;
	public Car(String name, int size) {
		System.out.println("constructor args...");
		this.name = name;
		this.size = size;
	}
	
	public Car() {
		System.out.println("constructor...");
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		System.out.println("set name...");
		this.name = name;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		System.out.println("set size...");
		this.size = size;
	}
	
	public void init() {
		System.out.println("init...");
	}
	
	
	public void destory() {
		System.out.println("destory...");
	}

	@Override
	public String toString() {
		return "Car [name=" + name + ", size=" + size + "]";
	}
	
}
