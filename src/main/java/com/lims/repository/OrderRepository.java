package com.lims.repository;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

import com.lims.entity.Order;

public interface OrderRepository extends DataTablesRepository<Order, Long> {

	Order findByUserUsernameAndBookBookIdAndStatusNotAndStatusNot(String username, long bookId, int statusReturn,
			int statusReject);


}
