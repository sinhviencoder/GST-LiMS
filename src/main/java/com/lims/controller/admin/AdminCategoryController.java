package com.lims.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lims.entity.Category;
import com.lims.service.CategoryService;

@Controller
@RequestMapping(value = "/admin/category")
public class AdminCategoryController {

	@Autowired
	CategoryService categoryService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(ModelMap model) {
		model.addAttribute("menuCategory", "active");
		model.addAttribute("menuSubCategory", "active");
		return "admin/admin-category";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getFormCategory(Category category, Model model) {
		model.addAttribute("categorys", categoryService.getCategoryAll());
		return "admin/admin-category-form";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String save(@Valid Category category, BindingResult bindResult, Model model) {
		model.addAttribute("categorys", categoryService.getCategoryAll());
		if (bindResult.hasErrors()) {
			return "admin/admin-category-form";
		}
		
		categoryService.save(category);
		
		return "redirect:/admin/category";
	}
	
	
	//Update Ajax khong thuan thuy ma du vao binding spring thymeleaf
	@RequestMapping(value = "/add-ajax", method = RequestMethod.GET)
	public String getFormCategoryAjax(Category category, Model model) {
		model.addAttribute("categorys", categoryService.getCategoryAll());
		return "admin/admin-category-form-ajax :: form";
	}
	
	@RequestMapping(value = "/add-ajax", method = RequestMethod.POST)
	public String saveAjax(@Valid Category category, BindingResult bindResult, Model model) {
		model.addAttribute("categorys", categoryService.getCategoryAll());
		if (bindResult.hasErrors()) {
			model.addAttribute("isSuccess", false);
			return "admin/admin-category-form-ajax :: form";
		}
		
		categoryService.save(category);
		model.addAttribute("isSuccess", true);
		model.addAttribute("category", new Category());
		return "admin/admin-category-form-ajax :: form";
	}

	@RequestMapping(value = "/datatable", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesOutput<Category> list(@Valid DataTablesInput input) {
		return categoryService.findAll(input);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteCategory( @PathVariable("id") long id) {
		
		categoryService.delete(id);
		
	}
}