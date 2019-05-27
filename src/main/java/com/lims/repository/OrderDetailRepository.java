package com.lims.repository;

import org.springframework.data.repository.CrudRepository;

import com.lims.entity.Order;

public interface OrderDetailRepository extends CrudRepository<Order, Long> {
}
