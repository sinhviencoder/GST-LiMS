package com.lims.repository;

import org.springframework.data.repository.CrudRepository;

import com.lims.entity.OrderDetail;

public interface OrderDetailRepository extends CrudRepository<OrderDetail, Long> {
}
