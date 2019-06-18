package com.lims.controller.api;

import java.util.Optional;

import javax.validation.Valid;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lims.config.AuthoritiesConstants;
import com.lims.entity.Category;
import com.lims.service.CategoryService;
import com.util.ErrorUtils;

@RestController // service restFull Api
@RequestMapping("/api/category")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
@Secured({ AuthoritiesConstants.ADMIN, AuthoritiesConstants.EMPLOYEE })
public class ApiCategoryController {
	private CategoryService categoryService;

	@Autowired
	public ApiCategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}

	// @PreAuthorize("permitAll()")
	@RequestMapping(method = RequestMethod.GET)
	public Iterable<Category> findAll() {
		return categoryService.getCategoryAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public String add(@Valid @RequestBody Category category, BindingResult result) {
		System.out.println("=========");
		if (result.hasErrors()) {
			return ErrorUtils.customErrors(result.getAllErrors());
		} else {
			if (category.getCategoryId() != null)
				return categoryService.update(category);
			return categoryService.save(category);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Optional<Category> findOne(@PathVariable long id) {
		return categoryService.getCategoryById(id);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public String update(@Validated @RequestBody Category category) {
		return categoryService.update(category);
	}

	@RequestMapping(value = "/{categoryId}", method = RequestMethod.DELETE)
	public String delete(@PathVariable long categoryId) {
		return categoryService.delete(categoryId);
	}
	@RequestMapping(value = "/{categoryId}", method = RequestMethod.PUT)
public String edit(@PathVariable long categoryId, Category category) {
	return categoryService.update(category);
	
}
	
}