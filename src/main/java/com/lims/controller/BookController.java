package com.lims.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lims.entity.Book;
import com.lims.repository.BookRepository;
import com.lims.service.BookService;
import com.lims.service.CategoryService;

@Controller
public class BookController {

	@Autowired
	BookService bookService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	BookRepository bookRepository;

	@RequestMapping(value = { "/book" }, method = RequestMethod.GET)
	public String pageBook(Model model, Book book,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) {

		page = page - 1;
		model.addAttribute("books", bookService.getBookdAll());
		Page<Book> bookPage = bookService.getBookAll(PageRequest.of(page, 8));
		model.addAttribute("bookPage", bookPage);
		model.addAttribute("categoryRoots", categoryService.getCategoryRoot());
		return "view/book";
	}

	@RequestMapping(value = "/book-page", method = RequestMethod.GET)
	public String pageBookPage(Model model, Book book,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		page = page - 1;
		model.addAttribute("books", bookService.getBookdAll());
		Page<Book> bookPage = bookService.getBookAll(PageRequest.of(page, 8));
		model.addAttribute("bookPage", bookPage);
		return "view/book-pagination :: #content";
	}

	@RequestMapping(value = "/book/cart/add", method = RequestMethod.GET)
	public String oderDetail(@RequestParam(value = "bookId", required = true) Long bookId, final Principal principal,
			Model model) {

		// check quantity book store >1
		// And check cout user order book (countOrder < (quantityBook -1))

		Book bookOrder = bookRepository.findByBookIdAndCountGreaterThanAndQuatityGreaterThan(bookId, 1, 1);
		System.out.println(bookOrder.getName());
		model.addAttribute("bookOrder", bookOrder);
		if (null == principal)
			return "view/book-order-cart";
		return "view/book-order-cart";
	}
}