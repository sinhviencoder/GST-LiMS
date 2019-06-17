package com.lims.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import org.springframework.data.jpa.repository.Query;

import com.lims.entity.Book;

public interface BookRepository extends DataTablesRepository<Book, Long> {

	Book findByBookIdAndQuantityActualGreaterThan(long bookId, int count);

	Book findTopByOrderByNameDesc();

	Page<Book> findByAuthorNameLike(String authorName, Pageable pageable);

	Page<Book> findByNameLike(String bookName, Pageable pageable);

	Page<Book> findByCategoryNameLike(String categoryName, Pageable pageable);

	Page<Book> findByAuthorNameLikeOrNameLikeOrCategoryNameLikeOrDescriptionLike(String authorName, String bookName,
			String categoryName, String description, Pageable pageable);
	@Query(value = "select book.book_id, book.name, book.author_id, book.category_id, book.image, book.status, book.description, book.quantity ,count(orders.book_id) as quantity_actual from orders join book on orders.book_id= book.book_id where month(orders.end_date)  between 5  and 7 group by book.book_id order by  quantity_actual desc limit 2;", nativeQuery = true)
	List<Book> topBook();
}
