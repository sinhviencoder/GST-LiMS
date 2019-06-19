package com.lims.controller.admin;

import javax.validation.Valid;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
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

import com.lims.entity.Author;
import com.lims.service.AuthorService;


@Controller

public class AdminAuthorController {

		@Autowired
	AuthorService authorService;

	@RequestMapping(value = "/admin/author", method = RequestMethod.GET)
	public String pageAuthor() {
		return "admin/admin-author";
	}

	@RequestMapping(value = "/admin/author/add", method = RequestMethod.GET)
	public String getForm(Model model, Author author) {
				model.addAttribute("name", author.getName());
				model.addAttribute("description ", author.getDescription());
		return "admin/admin-author-form :: form";
	}

	@RequestMapping(value = "/admin/author/add", method = RequestMethod.POST)
	public String addAuthor(@ModelAttribute("author") @Valid Author author, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {	
			return "admin/admin-author-form :: form";
		}
		authorService.save(author);
		return "admin/admin-author-form :: form";
	}
	
	@RequestMapping(value = "/{authorId}", method = RequestMethod.DELETE)
	public String delete(@PathVariable long authorId) {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("status", "success");
			authorService.delete(authorId);
			jsonObject.put("messages", "#" + authorId + " Xóa Thành Công");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

	@RequestMapping(value = "/admin/author/datatable", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesOutput<Author> getDatatable(@Valid DataTablesInput input) {
		return authorService.getAuthorAll(input);
	}
	
	@RequestMapping(value = "/admin/author/edit/{id}", method = RequestMethod.GET)
	public String getFormAuthorEdit(Author author, Model model, @PathVariable("id") long id) {
		
		Author authorByID= authorService.getAuthorById(id).get();
		
		model.addAttribute("authorByID", authorByID);
		return "admin/admin-author-edit-form";
	}
	
	@RequestMapping(value = "/admin/author/edit/{id}", method = RequestMethod.POST)
	public String edit(@Valid Author author, BindingResult bindResult, Model model,@PathVariable("id") long id) {
		if (bindResult.hasErrors()) {
			return "admin/admin-author-edit-form";
		}
		System.out.println(author.getAuthorId());
		authorService.update(author);
	
		return "redirect:/admin/author";
	}
	
}
