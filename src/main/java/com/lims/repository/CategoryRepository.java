package com.lims.repository;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import com.lims.entity.Category;

public interface CategoryRepository extends DataTablesRepository<Category, Long> {

	// Select category Perant
	List<Category> findByCategoryParentIsNull();

}
