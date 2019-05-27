package com.lims.controller.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lims.config.AuthoritiesConstants;
import com.lims.entity.User;
import com.lims.service.UserService;
import com.util.ErrorUtils;


@RestController
@RequestMapping("/api/user")
@Secured({ AuthoritiesConstants.ADMIN})
public class ApiUserController {

	private UserService userService;

	@Autowired
	// better go with constructor injection
	public ApiUserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<User> findAll() {
		return userService.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public String add(@Valid @RequestBody User user, BindingResult result) {
		if (result.hasErrors()) {
			return ErrorUtils.customErrors(result.getAllErrors());
		} else {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(hashedPassword);
			if (user.getUserId() != null)
				return userService.update(user);
			return userService.save(user);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public User findOne(@PathVariable Long id) {
		return userService.findOne(id).get();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public String update(@Validated @RequestBody User user, BindingResult result) {
		if (result.hasErrors()) {
			return ErrorUtils.customErrors(result.getAllErrors());
		} else {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(hashedPassword);
			return userService.update(user);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public String delete(@PathVariable long id) {
		return userService.delete(id);
	}

}