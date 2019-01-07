package com.daojian.springstudy.annotation;

import java.lang.annotation.Annotation;
import java.util.Arrays;

@Person(role="CEO")
@Person(role="husband")
@Person(role="father")
@Person(role="son")
public class Man {
	String name="";
	
	public static void main(String[] args) {
		Annotation[] annotations = Man.class.getAnnotations(); 
		System.out.println(annotations.length);
		System.out.println(Arrays.toString(annotations));
		Persons p1=(Persons) annotations[0];
		for(Person t:p1.value()){
        	System.out.println(t.role());
        }

	}
}
