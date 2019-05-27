package com.lims.service;

import java.util.List;
import java.util.Optional;

import com.lims.entity.Role;


public interface RoleService {
	Iterable<Role> findAll();

	List<Role> search(String q);

	Optional<Role> findOne(long id);

	String save(Role role);

	String delete(long id);

	String update(Role role);

}
