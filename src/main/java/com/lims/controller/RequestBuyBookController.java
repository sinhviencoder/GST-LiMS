package com.lims.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lims.entity.RequestBuyBook;
import com.lims.entity.User;
import com.lims.service.RequestService;
import com.lims.service.UserService;

@Controller
public class RequestBuyBookController {

	@Autowired
	RequestService requestService;
	
	@Autowired
	UserService userService;

	@RequestMapping(value = "/request-buy-book", method = RequestMethod.GET)
	public String requestBuyBook(Model model, RequestBuyBook requestBuyBook) {
		return "view/request-buy-book";
	}

	@RequestMapping(value = "/request-buy-book", method = RequestMethod.POST)
	public String requestBuyBook( Model model,RequestBuyBook requestBuyBook, BindingResult bindingResult, final  Principal principal ) {
		if (bindingResult.hasErrors()) {
			return "view/index";
			
		}
		if(principal == null) {
		return "view/login";
		}

		// final String currentUserName =
		// SecurityContextHolder.getContext().getAuthentication().getName();

		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = principal.getName();
		System.out.println("Lấy user"+userName);
		User user = userService.getUserByUsername(userName);
		
		requestBuyBook.setUser(user);
		requestService.save(requestBuyBook);
		model.addAttribute("success", "Thêm yêu cầu thành công");
		return "view/index";
	}

}
