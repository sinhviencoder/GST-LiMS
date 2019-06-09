package com.lims.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lims.entity.Book;
import com.lims.service.AuthorService;
import com.lims.service.BookService;
import com.lims.service.CategoryService;

@Controller
@RequestMapping(value = "/admin/author")
public class AdminAuthorController {

	@Autowired
	BookService bookService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	AuthorService authorService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String pageAuthor() {
		return "admin/admin-author";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getForm(Model model, Book book) {
		model.addAttribute("categorys", categoryService.getCategoryAll());
		model.addAttribute("authors", authorService.getAuthorAll());
		return "admin/admin-book-form :: form";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUser(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, Model model) {
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

	@RequestMapping(value = "/datatable", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesOutput<Book> getDatatable(@Valid DataTablesInput input) {
		return bookService.getBookAll(input);
	}
}
