package com.daojian.springstudy.annotation.repository;

import org.springframework.stereotype.Repository;

@Repository
public class JdbcRepositoryImpl implements UserRepository {

	public void save() {
		System.out.println("jdbc repository save...");
	}

}
