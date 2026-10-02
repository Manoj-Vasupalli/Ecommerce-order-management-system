package com.ecommerce.order.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.order.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	
	
	    private final OrderService service;
	    public OrderController(OrderService service) { this.service = service; }

	    @PostMapping
	    public ApiResponse<OrderEntity> placeOrder(@RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey,
	                                                @Valid @RequestBody OrderRequest request) {
	        return ApiResponse.success("Order placed", service.placeOrder(request, idempotencyKey));
	    }

	    @GetMapping("/{orderId}")
	    public ApiResponse<OrderEntity> get(@PathVariable String orderId) { return ApiResponse.success("Order fetched", service.findById(orderId)); }
	    @GetMapping("/customer/{customerId}")
	    public ApiResponse<List<OrderEntity>> byCustomer(@PathVariable String customerId) { return ApiResponse.success("Customer orders", service.findByCustomer(customerId)); }
	}

}
