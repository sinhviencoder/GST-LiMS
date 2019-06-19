package com.lims.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;
import com.lims.entity.RequestBuyBook;

public interface RequestBuyBookRepository extends   DataTablesRepository<RequestBuyBook, Long> {
}
