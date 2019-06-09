package com.lims.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import com.lims.entity.Book;
import com.lims.repository.BookRepository;
import com.lims.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	BookRepository bookRepository;

	@Override
	public Iterable<Book> getBookdAll() {
		return bookRepository.findAll();
	}


	@Override
	public List<Book> search(String q) {
		return null;
	}

	@Override
	public Optional<Book> getBookById(long id) {
		return bookRepository.findById(id);
	}

	@Override
	public Book save(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public void delete(long id) {
		bookRepository.deleteById(id);
	}

	@Override
	public Book update(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public DataTablesOutput<Book> getBookAll(DataTablesInput input) {
		return bookRepository.findAll(input);
	}

	@Override
	public Page<Book> getBookAll(Pageable pageable) {
		return bookRepository.findAll(pageable);
	}


	@Override
	public Book getBookCheckOrder(long bookId) {
		return null;
	}


}
