package com.daojian.springstudy.annotation.repository;

import org.springframework.stereotype.Service;

@Service
public class UserRepositoryImpl implements UserRepository {

	public void save() {
		System.out.println("UserRepository save");
	}

}
