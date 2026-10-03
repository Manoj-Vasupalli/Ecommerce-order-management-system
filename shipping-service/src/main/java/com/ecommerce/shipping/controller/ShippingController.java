package com.ecommerce.shipping.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.core.dto.ApiResponse;
import com.ecommerce.shipping.model.Shipment;
import com.ecommerce.shipping.service.ShippingService;

@RestController
@RequestMapping("/shipments")
public class ShippingController {
	private final ShippingService service;

	public ShippingController(ShippingService service) {
		this.service = service;
	}

	@GetMapping("/order/{orderId}")
	public ApiResponse<Shipment> getByOrder(@PathVariable String orderId) {
		return ApiResponse.success("Shipment fetched", service.findByOrderId(orderId));
	}
}
