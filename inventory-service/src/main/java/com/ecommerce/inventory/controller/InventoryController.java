package com.ecommerce.inventory.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.core.dto.ApiResponse;
import com.ecommerce.core.dto.InventoryRequest;
import com.ecommerce.inventory.model.Inventory;
import com.ecommerce.inventory.service.InventoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

	private final InventoryService service;

	public InventoryController(InventoryService service) {
		this.service = service;
	}

	@PostMapping
	public ApiResponse<Inventory> create(@RequestBody Inventory inventory) {
		return ApiResponse.success("Inventory created", service.create(inventory));
	}

	@GetMapping("/{productId}")
	public ApiResponse<Inventory> get(@PathVariable String productId) {
		return ApiResponse.success("Inventory fetched", service.findByProductId(productId));
	}

	@PostMapping("/reserve")
	public ApiResponse<Inventory> reserve(@Valid @RequestBody InventoryRequest request) {
		return ApiResponse.success("Inventory reserved", service.reserve(request));
	}

	@PostMapping("/release")
	public ApiResponse<Inventory> release(@Valid @RequestBody InventoryRequest request) {
		return ApiResponse.success("Inventory released", service.release(request));
	}

}
