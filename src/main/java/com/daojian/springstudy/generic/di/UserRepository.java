package com.daojian.springstudy.generic.di;

import org.springframework.stereotype.Repository;

@Repository(value="baseUserRepository")
public class UserRepository extends BaseRepository<User> {

}
