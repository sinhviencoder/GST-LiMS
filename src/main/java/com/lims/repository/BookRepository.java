package com.lims.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import com.lims.entity.Book;

public interface BookRepository extends DataTablesRepository<Book, Long> {

	Book findByBookIdAndQuantityActualGreaterThan(long bookId, int count);

	Book findTopByOrderByNameDesc();

	Page<Book> findByAuthorNameLike(String authorName, Pageable pageable);

	Page<Book> findByNameLike(String bookName, Pageable pageable);

	Page<Book> findByCategoryNameLike(String categoryName, Pageable pageable);
}
