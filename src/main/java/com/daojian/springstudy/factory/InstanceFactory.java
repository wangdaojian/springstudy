package com.daojian.springstudy.factory;

import java.util.HashMap;
import java.util.Map;

import com.daojian.springstudy.Car;

public class InstanceFactory {
	private Map<String, Car> cars = null;
	
	public InstanceFactory() {
		cars = new HashMap<String, Car>();
		cars.put("audi", new Car("奥迪", 33));
		cars.put("benchi", new Car("奔驰", 45));
	}
	
	public Car getCar(String name) {
		System.out.println("instance factory getCar...");
		return cars.get(name);
	}
}
