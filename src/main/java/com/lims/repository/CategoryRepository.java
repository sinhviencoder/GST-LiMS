package com.lims.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.lims.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {
	// update khong phu thuoc child
	@Transactional
	@Modifying
	// @Query(value = "UPDATE category c SET c.name = ? WHERE c.category_id = ?",
	// nativeQuery = true)
	@Query(value = "UPDATE Category c SET c.name = :#{#category.name}, c.categoryParent.categoryId =:#{#category.categoryParent.categoryId}, c.sortorder = :#{#category.sortorder}, c.icon = :#{#category.icon}, c.styleClass = :#{#category.styleClass}, c.status = :#{#category.status}, c.style = :#{#category.style} WHERE c.categoryId = :#{#category.categoryId}")
	int update(@Param("category") Category category);

	// Select category Perant
	List<Category> findByCategoryParentIsNull();

}
