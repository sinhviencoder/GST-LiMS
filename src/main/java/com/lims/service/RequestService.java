package com.lims.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.lims.entity.Book;
import com.lims.entity.RequestBuyBook;

public interface RequestService {
	Iterable<RequestBuyBook> getRequestBuyBookdAll();
	RequestBuyBook save(RequestBuyBook request);
	void delete(long id);
	Optional<RequestBuyBook> getRequestBuyBookById(long id);
	List<RequestBuyBook> search(String q);
	DataTablesOutput<RequestBuyBook> getRequestBuyBookAll(DataTablesInput input);
	RequestBuyBook updateRequestByID(RequestBuyBook request);
}
