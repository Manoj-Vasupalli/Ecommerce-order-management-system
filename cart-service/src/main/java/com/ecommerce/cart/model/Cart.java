package com.ecommerce.cart.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cart")
public class Cart {
	

	    @Id
	    private String id;
	    private String customerId;
	    private List<CartItem> items = new ArrayList<>();
	    private Instant updatedAt = Instant.now();

	    public String getId() { return id; }
	    public void setId(String id) { this.id = id; }
	    public String getCustomerId() { return customerId; }
	    public void setCustomerId(String customerId) { this.customerId = customerId; }
	    public List<CartItem> getItems() { return items; }
	    public void setItems(List<CartItem> items) { this.items = items; }
	    public Instant getUpdatedAt() { return updatedAt; }
	    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
	
}
