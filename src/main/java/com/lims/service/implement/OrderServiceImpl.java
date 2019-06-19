package com.lims.service.implement;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public DataTablesOutput<Order> getOrderAll(DataTablesInput input) {
		return orderRepository.findAll(input);
	}

	@Override
	public Optional<Order> getOrderByOrderId(long orderId) {
		return orderRepository.findById(orderId);
	}

	@Override
	public Order getOrderByUsernameAndBookIdAndStatusAndStatus(String username, Long bookId, int statusReturn,
			int statusReject) {
		return orderRepository.findByUserUsernameAndBookBookIdAndStatusNotAndStatusNot(username, bookId, statusReturn,
				statusReject);
	}

	@Override
	public Page<Order> getOrderByUserId(long userId, Pageable pageable) {
		return orderRepository.findByUserUserId(userId, pageable);
	}

	@Override
	public void cancelOrder() {
		
	}

	@Override
	public Order getOrderByOrderById(long orderId) {
		return orderRepository.findById(orderId).get();
	}

}
