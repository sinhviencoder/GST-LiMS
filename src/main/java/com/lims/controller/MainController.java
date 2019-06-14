package com.lims.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lims.entity.Book;
import com.lims.repository.UserRepository;
import com.lims.service.BookService;
import com.lims.service.CategoryService;

@Controller
public class MainController {
	@Autowired
	BookService bookService;
	
	@Autowired
	CategoryService categoryService;

	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome(Model model) {
		model.addAttribute("categoryRoots", categoryService.getCategoryRoot());

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		System.out.println(username);
		model.addAttribute("user", userRepository.findByUsername(username));
		List<Book> bookTop = bookService.getTopBook();

		model.addAttribute("bookTop", bookTop);
		return "view/index";
	}
	

	
}