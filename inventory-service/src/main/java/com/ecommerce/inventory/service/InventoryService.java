package com.ecommerce.inventory.service;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.ecommerce.core.dto.InventoryRequest;
import com.ecommerce.core.exception.BusinessException;
import com.ecommerce.inventory.model.Inventory;
import com.ecommerce.inventory.repo.InventoryRepository;

import jakarta.transaction.Transactional;

@Service
public class InventoryService {
	
	
	
	    private final InventoryRepository repository;
	    public InventoryService(InventoryRepository repository) { this.repository = repository; }

	    public Inventory create(Inventory inventory) { return repository.save(inventory); }

	    public Inventory findByProductId(String productId) { return repository.findByProductId(productId).orElseThrow(() -> new BusinessException("Inventory not found for product: " + productId)); }

	    @Transactional
	    public Inventory reserve(InventoryRequest request) {
	        Inventory inventory = findByProductId(request.productId());
	        int freeStock = inventory.getAvailableQuantity() - inventory.getReservedQuantity();
	        if (freeStock < request.quantity()) throw new BusinessException("Requested quantity is not available");
	        inventory.setReservedQuantity(inventory.getReservedQuantity() + request.quantity());
	        inventory.setUpdatedAt(Instant.now());
	        return repository.save(inventory);
	    }

	    @Transactional
	    public Inventory release(InventoryRequest request) {
	        Inventory inventory = findByProductId(request.productId());
	        inventory.setReservedQuantity(Math.max(0, inventory.getReservedQuantity() - request.quantity()));
	        inventory.setUpdatedAt(Instant.now());
	        return repository.save(inventory);
	    }
	
}
