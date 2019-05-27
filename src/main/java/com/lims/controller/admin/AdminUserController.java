package com.lims.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lims.entity.User;
import com.lims.repository.UserRepository;
import com.lims.service.RoleService;

/**
 * @author Nguyen Van Trinh
 *
 */
@Controller
public class AdminUserController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/admin/user", method = RequestMethod.GET)
	public String listUsers(Model model) {
		return "admin/users/admin-user";
	}
	
	@RequestMapping(value = "/admin/user/add", method = RequestMethod.GET)
	public String getForm(Model model, User user) {
		model.addAttribute("roles", roleService.findAll());
		return "admin/users/admin-user-form :: form";
	}

	@RequestMapping(value = "/admin/user/add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, Model model) {
		model.addAttribute("roles", roleService.findAll());
		if (bindingResult.hasErrors()) {
			model.addAttribute("isSuccess", false);
			return "admin/users/admin-user-form :: form";
		}
		userRepository.save(user);
		model.addAttribute("isSuccess", true);
		return "admin/users/admin-user-form :: form";
	}

	@RequestMapping(value = "/admin/user/login", method = RequestMethod.GET)
	public String login(Model model, String error, String logout) {
		if (error != null)
			model.addAttribute("error", "Your username and password is invalid.");
		if (logout != null)
			model.addAttribute("message", "You have been logged out successfully.");
		return "admin/users/admin-user-login";
	}

	@RequestMapping(value = "/admin/user/datatable", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesOutput<User> list(@Valid DataTablesInput input) {
		return userRepository.findAll(input);
	}
}
