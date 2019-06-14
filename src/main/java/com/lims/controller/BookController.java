package com.lims.controller;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lims.entity.Book;
import com.lims.entity.OrderDetail;
import com.lims.repository.BookRepository;
import com.lims.repository.OrderDetailRepository;
import com.lims.service.BookService;
import com.lims.service.CategoryService;

@Controller
public class BookController {

	@Autowired
	EntityManager em;

	@Autowired
	BookService bookService;

	@Autowired
	OrderDetailRepository orderDetailRepository;

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
	public String checkOrder(@RequestParam(value = "bookId", required = true) Long bookId, final Principal principal,
			Model model) {

		// check quantity book store >1 And check cout user order book (countOrder <
		// (quantityBook -1))
		// check user muon truoc do hay chua

		Book bookOrder = bookRepository.findByBookIdAndCountGreaterThanAndQuatityGreaterThan(bookId, 1, 1);
		System.out.println(bookOrder != null ? bookOrder.getName() : "");
		model.addAttribute("bookOrder", bookOrder);

		Date endDateOrder = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(endDateOrder);
		c.add(Calendar.DATE, 2);
		endDateOrder = c.getTime();

		model.addAttribute("endDateOrder", endDateOrder);
		if (null == principal)
			return "view/book-order-cart";
		return "view/book-order-cart";
	}

	@RequestMapping(value = "/book/order/confirm", method = RequestMethod.GET)
	public String confirmOrder(@RequestParam(value = "bookId", required = true) Long bookId, final Principal principal,
			Model model) {

		// check quantity book store >1
		// And check cout user order book (countOrder < (quantityBook -1))

		Book bookOrder = bookRepository.findByBookIdAndCountGreaterThanAndQuatityGreaterThan(bookId, 1, 1);
		System.out.println(bookOrder != null ? bookOrder.getName() : "");
		model.addAttribute("bookOrder", bookOrder);
		if (bookOrder == null)
			return "view/book-order-cart";

		Date endDateOrder = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(endDateOrder);
		c.add(Calendar.DATE, 2);
		endDateOrder = c.getTime();

		
		model.addAttribute("endDateOrder", endDateOrder);
		if (null == principal)
			return "view/book-order-cart";

		// update book count
		bookOrder.setCount(bookOrder.getCount() - 1);
		bookOrder.setName("dkm");

		System.out.println(bookOrder.getCount() + "ok1");
		
		//System.out.println("After EntityManager.persist() : " + em.contains(bookOrder));
		//em.detach(bookOrder);
//		 bookService.save(bookOrder);

		OrderDetail orderBook = new OrderDetail();
		orderBook.setBook(bookOrder);
		orderBook.setEndDate(endDateOrder);
		
		Book bookOrder1 = bookRepository.findById(1l).get();
		System.out.println(bookOrder1.getCount() + "ok2");
		System.out.println(bookOrder1.getName() + "ok2");

		//orderDetailRepository.save(orderBook);

		return "view/book-order-cart-confirm";
	}
}