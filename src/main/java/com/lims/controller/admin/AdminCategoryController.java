package com.lims.controller.admin;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminCategoryController {

	@PersistenceContext
	private EntityManager entityManager;
	
	@GetMapping("/admin/category")
	public String index(ModelMap model) {
		model.addAttribute("menuCategory", "active");
		model.addAttribute("menuSubCategory", "active");
		return "admin.CategoryPage";
	}
}