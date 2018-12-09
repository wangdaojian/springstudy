package com.daojian.springstudy.factory;

import java.util.HashMap;
import java.util.Map;

import com.daojian.springstudy.Car;

public class StaticCarFactory {

	private static Map<String, Car> map = new HashMap<String, Car>();
	
	static {
		map.put("audi", new Car("奥迪", 12));
		map.put("benchi", new Car("奔驰", 32));
	}
	
	public static Car getCar(String name) {
		System.out.println("static factory getCar...");
		return map.get(name);
	}
}
