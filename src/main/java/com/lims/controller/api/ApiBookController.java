package com.lims.controller.api;

import java.util.Optional;

import javax.validation.Valid;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lims.entity.Book;
import com.lims.service.BookService;

@RestController
@RequestMapping("/api/book")
public class ApiBookController {
	@Autowired
	private BookService bookService;

	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Book> findAll() {
		return bookService.getBookdAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public String add(@Valid @RequestBody Book book, BindingResult result) {
		return null;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Optional<Book> findOne(@PathVariable long id) {
		return bookService.getBookById(id);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public Book update(@Validated @RequestBody Book book) {
		return bookService.save(book);
	}

	@RequestMapping(value = "/{bookId}", method = RequestMethod.DELETE)
	public String delete(@PathVariable long bookId) {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("status", "success");
			bookService.delete(bookId);
			jsonObject.put("messages", "#" + bookId + " Xóa Thành Công");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

}