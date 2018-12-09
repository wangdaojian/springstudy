package com.daojian.springstudy.annotation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.daojian.springstudy.annotation.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	@Qualifier("userRepositoryImpl")
	private UserRepository userRespository;

	public void add() {
		System.out.println("UserService add...");
		userRespository.save();
	}
}
