package com.lims.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import com.lims.entity.Order;
import com.lims.repository.OrderRepository;
import com.lims.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	@Override
	public void save(Order order) {
		orderRepository.save(order);
	}

	@Override
	public Order getOrderByUsernameAndBookId(String username, Long bookId) {
		return orderRepository.findByUserUsernameAndBookBookId(username, bookId);
	}

	@Override
	public DataTablesOutput<Order> getOrderAll(DataTablesInput input) {
		return orderRepository.findAll(input);
	}

}
