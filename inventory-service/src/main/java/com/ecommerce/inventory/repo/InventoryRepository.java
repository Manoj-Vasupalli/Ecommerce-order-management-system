package com.ecommerce.inventory.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.inventory.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, String> {
	 Optional<Inventory> findByProductId(String productId);
}
