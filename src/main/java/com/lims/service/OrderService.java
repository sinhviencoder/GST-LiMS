package com.lims.service;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.lims.entity.Order;

public interface OrderService {

	Order getOrderByUsernameAndBookId(String username, Long bookId);

	void save(Order order);

	DataTablesOutput<Order> getOrderAll( DataTablesInput input);

}
