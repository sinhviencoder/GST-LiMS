package com.lims.controller.api;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lims.config.AuthoritiesConstants;
import com.lims.entity.Role;
import com.lims.service.RoleService;
import com.util.ErrorUtils;

@RestController
@RequestMapping("/api/role")
@Secured({ AuthoritiesConstants.ADMIN })
public class ApiRoleController {

	private RoleService roleService;

	@Autowired
	// better go with constructor injection
	public ApiRoleController(RoleService roleService) {
		super();
		this.roleService = roleService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Role> findAll() {
		return roleService.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public String add(@Valid @RequestBody Role role, BindingResult result) {
		if (result.hasErrors()) {
			return ErrorUtils.customErrors(result.getAllErrors());
		} else {
			return roleService.save(role);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Optional<Role> findOne(@PathVariable long id) {
		return roleService.findOne(id);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@Valid @RequestBody Role role, BindingResult result) {
		if (result.hasErrors()) {
			return ErrorUtils.customErrors(result.getAllErrors());
		} else {
			return roleService.save(role);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable long id) {
		return roleService.delete(id);
	}

}