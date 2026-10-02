package com.ecommerce.cart.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.cart.model.Cart;

public interface CartRepository extends JpaRepository<Cart, String>{
	 Optional<Cart> findByCustomerId(String customerId);
}
