package com.lims.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.lims.entity.Category;

public interface CategoryService {

	Iterable<Category> getCategoryAll();

	List<Category> getCategoryTree();

	List<Category> search(String q);

	Optional<Category> getCategoryById(long id);

	String save(Category category);

	String delete(long id);

	String update(Category category);

	DataTablesOutput<Category> findAll(DataTablesInput input);

}