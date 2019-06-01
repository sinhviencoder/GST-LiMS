package com.lims.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lims.entity.Book;
import com.lims.service.BookService;

@Controller
public class BooksController {
	@Autowired
	BookService bookService;

	@RequestMapping(value = "/book", method = RequestMethod.GET)
	public String pageBook(Model model, Book book,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page
			) {
		model.addAttribute("books", bookService.getBookdAll());
		Page<Book> pageBook = bookService.getBookAll(new PageRequest(page, 8));
		model.addAttribute("bookPage", pageBook.getContent());
		int totalPage = pageBook.getTotalPages();
		if(totalPage>0) {
			List<Integer> pageNumbers = new ArrayList<>();
			for (int i = 0; i < totalPage; i++) {
				pageNumbers.add(i+1);
			}
			model.addAttribute("pageNumbers",pageNumbers);
			
		}
		return "views/books/book";
	}
//	@RequestMapping(value = "/book/list-book/page/{page}", method = RequestMethod.GET)
//	
//	public Page<Book> viewBookPage(Model model,
//			@RequestParam(value = "page", required = false, defaultValue = "0") int page) {
//		Page<Book> pageBook = bookService.getBookAll(new PageRequest(page, 8));
//		model.addAttribute("bookPage", pageBook);
//		System.out.println("tao ne hihi"+ pageBook.getNumber());
//	
//		return bookService.getBookAll(new PageRequest(page, 1));
//	}
	
}