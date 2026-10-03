package com.ecommerce.product.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.core.dto.ApiResponse;
import com.ecommerce.product.model.Product;
import com.ecommerce.product.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	private final ProductService service;

	public ProductController(ProductService service) {
		this.service = service;
	}

	@PostMapping
	public ApiResponse<Product> create(@RequestBody Product product) {
		return ApiResponse.success("Product created", service.create(product));
	}

	@GetMapping("/{id}")
	public ApiResponse<Product> get(@PathVariable String id) {
		return ApiResponse.success("Product found", service.findById(id));
	}

	@GetMapping("/search")
	public ApiResponse<List<Product>> search(@RequestParam String keyword) {
		return ApiResponse.success("Search result", service.search(keyword));
	}

	@GetMapping("/category/{category}")
	public ApiResponse<List<Product>> category(@PathVariable String category) {
		return ApiResponse.success("Category result", service.findByCategory(category));
	}

}
