package com.ecommerce.cart.service;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.ecommerce.cart.model.Cart;
import com.ecommerce.cart.model.CartItem;
import com.ecommerce.cart.repo.CartRepository;

@Service
public class CartService {
	
	    private final CartRepository repository;
	    public CartService(CartRepository repository) { this.repository = repository; }

	    public Cart addItem(String customerId, CartItem item) {
	        Cart cart = repository.findByCustomerId(customerId).orElseGet(() -> {
	            Cart c = new Cart();
	            c.setCustomerId(customerId);
	            return c;
	        });
	        cart.getItems().add(item);
	        cart.setUpdatedAt(Instant.now());
	        return repository.save(cart);
	    }

	    public Cart getCart(String customerId) { return repository.findByCustomerId(customerId).orElseGet(() -> { Cart c = new Cart(); c.setCustomerId(customerId); return c; }); }

	    public void clearCart(String customerId) { repository.findByCustomerId(customerId).ifPresent(repository::delete); }
	

}
