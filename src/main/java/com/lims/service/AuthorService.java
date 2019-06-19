package com.lims.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.lims.entity.Author;

public interface AuthorService {

	Iterable<Author> getAuthorAll();

	List<Author> search(String q);

	Optional<Author> getAuthorById(long id);

	Author save(Author author);

	void delete(long id);

	Author update(Author author);

	DataTablesOutput<Author> getAuthorAll( DataTablesInput input);

}