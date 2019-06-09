package com.lims.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.lims.entity.Book;

public interface BookService {

	Iterable<Book> getBookdAll();

	Page<Book> getBookAll(Pageable pageable);
	
	DataTablesOutput<Book> getBookAll(DataTablesInput input);

	List<Book> search(String q);

	Optional<Book> getBookById(long id);

	Book save(Book book);

	void delete(long bookId);

	Book update(Book book);

	Book getBookCheckOrder(long bookId);
}