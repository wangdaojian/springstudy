package com.daojian.springstudy;

import java.util.List;

public class Person {
	
	private String name;
	
	private Car car;
	
	private List<Car> cars;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}
	

	public Person() {
		System.out.println("consturct ...");
	}

	public Person(String name, Car car) {
		this.name = name;
		this.car = car;
	}

	
	@Override
	public String toString() {
		return "Person [name=" + name + ", car=" + car + ", cars=" + cars + "]";
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	
	
	
}
