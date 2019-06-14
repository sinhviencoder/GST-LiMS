package com.lims.repository;

import java.util.List;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;

import com.lims.entity.Book;

public interface BookRepository extends DataTablesRepository<Book, Long> {

	Book findByBookIdAndCountGreaterThanAndQuatityGreaterThan(long bookId, int count, int quantity);
	@Query(value = "select book.book_id, book.name, book.author_id, book.category_id, book.image, book.status, book.description, book.quatity ,count(order_detail.book_id) as count from order_detail join book on order_detail.book_id= book.book_id where month(order_detail.end_date)  between 5  and 6 group by book.book_id order by  count desc limit 3;", nativeQuery = true)
	List<Book> topBook();
}
