package com.daojian.springstudy.factory;

import org.springframework.beans.factory.FactoryBean;

import com.daojian.springstudy.Car;

public class CarFactoryBean implements FactoryBean<Car> {
	
	private String brand;
	
	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Car getObject() throws Exception {
		System.out.println("factory bean getObject...");
		return new Car(brand, 99);
	}

	public Class<?> getObjectType() {
		return Car.class;
	}

	public boolean isSingleton() {
		return true;
	}

	
}
