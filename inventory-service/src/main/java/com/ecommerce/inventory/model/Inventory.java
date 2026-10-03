package com.ecommerce.inventory.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

@Entity
@Table(name = "inventory")
public class Inventory {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.UUID)
	    private String id;
	    @Column(unique = true, nullable = false)
	    private String productId;
	    private int availableQuantity;
	    private int reservedQuantity;
	    @Version
	    private Long version;
	    private Instant updatedAt = Instant.now();

	    public String getId() { return id; }
	    public void setId(String id) { this.id = id; }
	    public String getProductId() { return productId; }
	    public void setProductId(String productId) { this.productId = productId; }
	    public int getAvailableQuantity() { return availableQuantity; }
	    public void setAvailableQuantity(int availableQuantity) { this.availableQuantity = availableQuantity; }
	    public int getReservedQuantity() { return reservedQuantity; }
	    public void setReservedQuantity(int reservedQuantity) { this.reservedQuantity = reservedQuantity; }
	    public Long getVersion() { return version; }
	    public void setVersion(Long version) { this.version = version; }
	    public Instant getUpdatedAt() { return updatedAt; }
	    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
	

}
