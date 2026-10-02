package com.ecommerce.cart.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.cart.model.Cart;
import com.ecommerce.cart.model.CartItem;
import com.ecommerce.cart.service.CartService;
import com.ecommerce.core.dto.ApiResponse;

@RestController
@RequestMapping("/cart")
public class CartController {

	private final CartService service;

	public CartController(CartService service) {
		this.service = service;
	}

	@PostMapping("/{customerId}/items")
	public ApiResponse<Cart> addItem(@PathVariable String customerId, @RequestBody CartItem item) {
		return ApiResponse.success("Item added", service.addItem(customerId, item));
	}

	@GetMapping("/{customerId}")
	public ApiResponse<Cart> getCart(@PathVariable String customerId) {
		return ApiResponse.success("Cart fetched", service.getCart(customerId));
	}

	@DeleteMapping("/{customerId}")
	public ApiResponse<Void> clear(@PathVariable String customerId) {
		service.clearCart(customerId);
		return ApiResponse.success("Cart cleared", null);
	}

}
