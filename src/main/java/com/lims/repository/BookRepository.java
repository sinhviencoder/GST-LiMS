package com.lims.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import com.lims.entity.Book;

public interface BookRepository extends DataTablesRepository<Book, Long> {

	Book findByBookIdAndQuantityActualGreaterThan(long bookId, int count);
}
