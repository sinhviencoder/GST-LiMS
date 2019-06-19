package com.lims.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lims.entity.Book;
import com.lims.service.AuthorService;
import com.lims.service.BookService;
import com.lims.service.CategoryService;

@Controller
public class AdminBookController {

	@Autowired
	BookService bookService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	AuthorService authorService;

	@RequestMapping(value = "/admin/book", method = RequestMethod.GET)
	public String pageBook() {
		return "admin/admin-book";
	}

	@RequestMapping(value = "/admin/book/add", method = RequestMethod.GET)
	public String getForm(Model model, Book book) {
		model.addAttribute("categorys", categoryService.getCategoryAll());
		model.addAttribute("authors", authorService.getAuthorAll());
		return "admin/admin-book-form :: form";
	}

	@RequestMapping(value = "/admin/book/add", method = RequestMethod.POST)
	public String addBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, Model model) {
		model.addAttribute("categorys", categoryService.getCategoryAll());
		model.addAttribute("authors", authorService.getAuthorAll());
		if (bindingResult.hasErrors()) {
			model.addAttribute("isSuccess", false);
			return "admin/admin-book-form :: form";
		}
		bookService.save(book);
		model.addAttribute("isSuccess", true);

		return "admin/admin-book-form :: form";
	}

	@RequestMapping(value = "/admin/book/edit/{bookId}", method = RequestMethod.GET)
	public String getFormEdit(Model model, @PathVariable(value = "bookId") long bookId) {

		model.addAttribute("book", bookService.getBookById(bookId).get());
		model.addAttribute("categorys", categoryService.getCategoryAll());
		model.addAttribute("authors", authorService.getAuthorAll());
		return "admin/admin-book-edit-form";
	}

	@RequestMapping(value = "/admin/book/edit/{bookId}", method = RequestMethod.POST)
	public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, Model model,
			@PathVariable(value = "bookId") long bookId) {
		model.addAttribute("categorys", categoryService.getCategoryAll());
		model.addAttribute("authors", authorService.getAuthorAll());
		if (bindingResult.hasErrors()) {
			return "redirect: /admin/book";
		}
		bookService.save(book);
		model.addAttribute("isSuccess", true);
		return "admin/admin-book-form :: form";
	}

	@RequestMapping(value = "/admin/book/datatable", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesOutput<Book> getDatatable(@Valid DataTablesInput input) {
		return bookService.getBookAll(input);
	}
}
