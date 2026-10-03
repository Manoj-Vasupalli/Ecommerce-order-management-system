package com.ecommerce.shipping.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.ecommerce.core.event.OrderConfirmedEvent;
import com.ecommerce.core.event.ShipmentCreatedEvent;
import com.ecommerce.shipping.model.Shipment;
import com.ecommerce.shipping.repo.ShipmentRepository;

@Service
public class ShippingService {
	  private final ShipmentRepository repository;
	    private final RabbitTemplate rabbitTemplate;
	    public ShippingService(ShipmentRepository repository, RabbitTemplate rabbitTemplate) { this.repository = repository; this.rabbitTemplate = rabbitTemplate; }

	    @RabbitListener(queues = "shipping.order.confirmed.queue")
	    public void createShipment(OrderConfirmedEvent event) {
	        Shipment shipment = new Shipment();
	        shipment.setId("SHP-" + UUID.randomUUID());
	        shipment.setOrderId(event.orderId());
	        shipment.setCourierPartner("FastShip Courier");
	        shipment.setTrackingNumber("TRK" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
	        shipment.setShipmentStatus("CREATED");
	        Shipment saved = repository.save(shipment);
	        rabbitTemplate.convertAndSend("urbancart.exchange", "shipment.created",
	                new ShipmentCreatedEvent(saved.getOrderId(), saved.getTrackingNumber(), saved.getCourierPartner(), Instant.now()));
	    }

	    public Shipment findByOrderId(String orderId) { return repository.findByOrderId(orderId).orElse(null); }
}
