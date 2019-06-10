package com.lims.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lims.entity.Book;
import com.lims.service.BookService;

@Controller
public class BooksController {

	
	@Autowired
	BookService bookService;

	@RequestMapping(value = {"/book"}, method = RequestMethod.GET)
	public String pageBook(Model model, Book book,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) {

		page = page - 1;
		model.addAttribute("books", bookService.getBookdAll());
		Page<Book> bookPage = bookService.getBookAll(new PageRequest(page, 8));
		model.addAttribute("bookPage", bookPage);
		return "views/book";
	}
  
	@RequestMapping(value = "/book-page", method = RequestMethod.GET)
	public String pageBookPage(Model model, Book book,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) {

		page = page - 1;
		model.addAttribute("books", bookService.getBookdAll());
		Page<Book> bookPage = bookService.getBookAll(new PageRequest(page, 8));
		model.addAttribute("bookPage", bookPage);
		return "views/book-page :: #content";
	}
	
	//	return bookService.getTopBook();
	
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
//	@ResponseBody
	public String getDetailBook(Model model, @PathVariable("id") int id
			) {
		
		Optional<Book> bookByID =bookService.getBookById(id);
	//	
		
		System.out.println("gau gau" +id);
//		System.out.println("t goi m"+bookByID.get().toString());
		
		model.addAttribute("bookByID", bookByID.get());
	
		return"views/book-detail";
	}
}