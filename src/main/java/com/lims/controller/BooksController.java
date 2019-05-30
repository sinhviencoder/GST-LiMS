package com.lims.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lims.entity.Book;
import com.lims.service.BookService;

@Controller
public class BooksController {
	@Autowired
	BookService bookService;

	@RequestMapping(value = "/book", method = RequestMethod.GET)
	public String pageBook(Model model, Book book) {
		model.addAttribute("books", bookService.getBookdAll());
		return "views/books/book";
	}
	
	
}