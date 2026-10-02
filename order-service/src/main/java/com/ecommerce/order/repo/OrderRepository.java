package com.ecommerce.order.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.order.model.OrderEntity;
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String> {
	  List<OrderEntity> findByCustomerId(String customerId);
	    Optional<OrderEntity> findByIdempotencyKey(String idempotencyKey);
}
