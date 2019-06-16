package com.lims.controller;

import java.security.Principal;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.lims.entity.Book;
import com.lims.entity.Order;
import com.lims.repository.BookRepository;
import com.lims.service.BookService;
import com.lims.service.CategoryService;
import com.lims.service.OrderService;
import com.lims.service.UserService;

@Controller
public class BookController {

	@Autowired
	BookService bookService;

	@Autowired
	OrderService orderService;

	@Autowired
	UserService userService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	BookRepository bookRepository;

	@RequestMapping(value = { "/book" }, method = RequestMethod.GET)
	public String pageBook(Model model, Book book,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) {

		page = page - 1;
		Page<Book> bookPage = bookService.getBookAll(PageRequest.of(page, 8));
		model.addAttribute("bookPage", bookPage);
		model.addAttribute("categoryRoots", categoryService.getCategoryRoot());
		return "view/book";
	}

	@RequestMapping(value = { "/book/search" }, method = RequestMethod.GET)
	public String pageBookSearch(Model model, Book book,
			@RequestParam(value = "search-type", required = false) String searchType,
			@RequestParam(value = "search-text", required = false) String searchText) {

		Page<Book> bookPage = null;

		if (searchType.equalsIgnoreCase("author")) {
			bookPage = bookService.findByAuthorNameLike(searchText, PageRequest.of(0, 16));
		} else if (searchType.equalsIgnoreCase("bookName")) {
			bookPage = bookService.findByNameLike(searchText, PageRequest.of(0, 16));
		} else if (searchType.equalsIgnoreCase("category")) {
			bookPage = bookService.findByCategoryNameLike(searchText, PageRequest.of(0, 16));
		} else {
			// fulltext
			bookPage = bookService.findByNameLike(searchText, PageRequest.of(0, 16));

		}
		model.addAttribute("bookPage", bookPage);
		model.addAttribute("categoryRoots", categoryService.getCategoryRoot());
		return "view/book";
	}

	@RequestMapping(value = "/book-page", method = RequestMethod.GET)
	public String pageBookPage(Model model, Book book,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		page = page - 1;
		Page<Book> bookPage = bookService.getBookAll(PageRequest.of(page, 8));
		model.addAttribute("bookPage", bookPage);
		return "view/book-pagination :: #content";
	}

	@RequestMapping(value = "/book/cart/add", method = RequestMethod.GET)
	public String checkOrder(@RequestParam(value = "bookId", required = true) Long bookId, final Principal principal,
			Model model) {

		// check login
		if (null == principal) {
			model.addAttribute("message", "Vui lòng đăng nhập trước khi mượn sách");
			return "view/book-order-cart :: login";
		}

		// Check co dang order book nay khong (muon doc ma chua tra)
		// status 4 la da return
		Order orderTmp = orderService.getOrderByUsernameAndBookIdAndStatusAndStatus(principal.getName(), bookId, 4, 3);
		if (orderTmp != null) {
			model.addAttribute("message", "Bạn đã đặt mượn sách từ trước, mỗi người chỉ đặt tối đa một quyển");
			return "view/book-order-cart :: orderNotReturn";
		}

		// check count book (count > 1)
		// neu book het cho phep dat truoc
		Book bookOrder = bookRepository.findByBookIdAndQuantityActualGreaterThan(bookId, 1);
		if (bookOrder == null) {

			model.addAttribute("message", "Hiện tại sách được mượn hết bạn có thể đặt giửa chổ trước");
			Date endDateOrder = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(endDateOrder);
			c.add(Calendar.DATE, 15);
			endDateOrder = c.getTime();
			model.addAttribute("endDateOrder", endDateOrder);
			model.addAttribute("bookOrder", bookRepository.findById(bookId).get());
			return "view/book-order-cart :: reserve";

		} else {

			model.addAttribute("message", "Tiếng hành đặt sách");

			Date endDateOrder = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(endDateOrder);
			c.add(Calendar.DATE, 15);
			endDateOrder = c.getTime();
			model.addAttribute("endDateOrder", endDateOrder);
			model.addAttribute("bookOrder", bookOrder);
		}

		return "view/book-order-cart :: order";
	}

	@RequestMapping(value = "/book/order/confirm", method = RequestMethod.GET)
	public String confirmOrder(@RequestParam(value = "bookId", required = true) Long bookId, final Principal principal,
			Model model) {

		// check login
		if (null == principal) {
			model.addAttribute("message", "Vui lòng đăng nhập trước khi mượn sách");
			return "view/book-order-cart";
		}

		// Check co dang order book nay khong (muon doc ma chua tra)
		Order orderTmp = orderService.getOrderByUsernameAndBookIdAndStatusAndStatus(principal.getName(), bookId, 4, 3);
		if (orderTmp != null) {
			model.addAttribute("message", "Bạn đã đặt mượn sách từ trước, mỗi người chỉ đặt tối đa một quyển");
			return "view/book-order-cart";
		}

		// check count book (count > 1)
		// neu book het cho phep dat truoc
		Book bookOrder = bookRepository.findByBookIdAndQuantityActualGreaterThan(bookId, 1);
		if (bookOrder == null) {
			model.addAttribute("message", "Tiếng hành đặt trước sách");

			Order order = new Order();
			order.setUser(userService.getUserByUsername(principal.getName()));

			Date endDateOrder = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(endDateOrder);
			c.add(Calendar.DATE, 15);
			endDateOrder = c.getTime();
			order.setStartDate(new Date());
			order.setEndDate(endDateOrder);

			order.setStatus(5);
			bookOrder = bookRepository.findById(bookId).get();
			bookOrder.setQuantityActual(bookOrder.getQuantityActual() - 1);
			order.setBook(bookOrder);

			bookService.save(bookOrder);
			orderService.save(order);
			model.addAttribute("order", order);
			return "view/book-order-cart-success";
		} else {
			model.addAttribute("message", "Tiếng hành đặt sách");

			Order order = new Order();
			order.setUser(userService.getUserByUsername(principal.getName()));

			Date endDateOrder = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(endDateOrder);
			c.add(Calendar.DATE, 15);
			endDateOrder = c.getTime();
			order.setStartDate(new Date());
			order.setEndDate(endDateOrder);

			// 0 la order truoc nhung chua co sach muon
			// 1 la order chua nhan
			// 2 la da nhan thanh cong
			// 3 la qua han
			// 4 la da return
			order.setStatus(1);
			// update book count
			bookOrder.setQuantityActual(bookOrder.getQuantityActual() - 1);
			order.setBook(bookOrder);

			bookService.save(bookOrder);
			orderService.save(order);
			model.addAttribute("order", order);
			return "view/book-order-cart-success";
		}
	}
}