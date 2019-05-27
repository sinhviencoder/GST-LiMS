package com.lims.repository;

import org.springframework.data.repository.CrudRepository;

import com.lims.entity.OrderDetail;

public interface OrderRepository extends CrudRepository<OrderDetail, Long> {
	
}
