package com.lims.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lims.entity.Category;
import com.lims.service.CategoryService;

@Controller
@RequestMapping(value = "/admin")
public class AdminCategoryController {

	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/category")
	public String index(ModelMap model) {
		model.addAttribute("menuCategory", "active");
//		model.addAttribute("menuSubCategory", "active");
		return "admin/categories/admin-category";
	}
	
	@RequestMapping(value = "category/datatable", method = RequestMethod.GET)
	@ResponseBody
	public DataTablesOutput<Category> list(@Valid DataTablesInput input) {
		return categoryService.findAll(input);
	}
}