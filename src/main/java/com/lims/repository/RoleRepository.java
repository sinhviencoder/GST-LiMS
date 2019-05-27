package com.lims.repository;

import org.springframework.data.repository.CrudRepository;

import com.lims.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
	Role findByName(String roleName);
}
