package com.lims.service;

import java.util.Optional;

import com.lims.entity.User;


public interface UserService {

	User getUserByUsername(String username);

	Iterable<User> findAll();

	Optional<User> findOne(long id);

	String save(User user);

	String update(User user);

	String delete(long id);

}
