package com.ecommerce.order.service;

import java.util.List;

import com.ecommerce.core.dto.OrderRequest;
import com.ecommerce.order.model.OrderEntity;

public interface OrderService {
	 public OrderEntity placeOrder(OrderRequest request, String idempotencyKey);
	 public OrderEntity findById(String id);
	 public List<OrderEntity> findByCustomer(String customerId);
}
