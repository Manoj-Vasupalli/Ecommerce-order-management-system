package com.ecommerce.shipping.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.shipping.model.Shipment;
@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, String> {
	  Optional<Shipment> findByOrderId(String orderId);
}
