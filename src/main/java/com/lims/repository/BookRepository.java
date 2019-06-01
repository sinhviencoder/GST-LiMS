package com.lims.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import com.lims.entity.Book;

public interface BookRepository extends DataTablesRepository<Book, Long> {
	Page<Book> findAll(Pageable pageable);
}
