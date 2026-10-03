package com.ecommerce.product.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.core.exception.BusinessException;
import com.ecommerce.product.model.Product;
import com.ecommerce.product.repo.ProductRepository;

@Service
public class ProductService {
	private final ProductRepository repository;

	public ProductService(ProductRepository repository) {
		this.repository = repository;
	}

	public Product create(Product product) {
		return repository.save(product);
	}

	public Product findById(String id) {
		return repository.findById(id).orElseThrow(() -> new BusinessException("Product not found: " + id));
	}

	public List<Product> search(String keyword) {
		return repository.findByNameContainingIgnoreCase(keyword);
	}

	public List<Product> findByCategory(String category) {
		return repository.findByCategoryIgnoreCase(category);
	}
}
