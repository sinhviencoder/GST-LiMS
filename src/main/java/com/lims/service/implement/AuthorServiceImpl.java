package com.lims.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import com.lims.entity.Author;
import com.lims.repository.AuthorRepository;
import com.lims.service.AuthorService;

@Service
public class AuthorServiceImpl implements AuthorService {
	@Autowired
	AuthorRepository authorRepository;

	@Override
	public Iterable<Author> getAuthorAll() {
		return authorRepository.findAll();
	}

	@Override
	public List<Author> search(String q) {
		return null;
	}

	@Override
	public Optional<Author> getAuthorById(long id) {
		return authorRepository.findById(id);
	}

	@Override
	public Author save(Author author) {
		return authorRepository.save(author);
	}

	@Override
	public void delete(long id) {
		 authorRepository.deleteById(id);;
	}

	@Override
	public Author update(Author author) {
		return authorRepository.save(author);
	}

	@Override
	public DataTablesOutput<Author> getAuthorAll(DataTablesInput input) {
		return authorRepository.findAll(input);
	}

}
