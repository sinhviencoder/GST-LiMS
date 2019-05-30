package com.lims.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/book")
public class BooksController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String pageBook() {
		return "views/books/book1";
	}

}