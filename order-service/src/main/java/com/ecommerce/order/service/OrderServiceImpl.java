package com.ecommerce.order.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.core.dto.InventoryRequest;
import com.ecommerce.core.dto.OrderRequest;
import com.ecommerce.core.event.OrderConfirmedEvent;
import com.ecommerce.core.exception.BusinessException;
import com.ecommerce.order.model.OrderEntity;
import com.ecommerce.order.repo.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepository repository;
	@Autowired
	private RabbitTemplate rabbitTemplate;
	@Autowired
	private RestTemplate restTemplate;

	

	@Override
	@Transactional
	public OrderEntity placeOrder(OrderRequest request, String idempotencyKey) {
		if (idempotencyKey != null) {
			var existing = repository.findByIdempotencyKey(idempotencyKey);
			if (existing.isPresent())
				return existing.get();
		}

		restTemplate.postForObject("http://localhost:8083/inventory/reserve",
				new InventoryRequest(request.productId(), request.quantity()), Object.class);

		OrderEntity order = new OrderEntity();
		order.setId("ORD-" + UUID.randomUUID());
		order.setCustomerId(request.customerId());
		order.setProductId(request.productId());
		order.setQuantity(request.quantity());
		order.setAmount(request.amount());
		order.setPaymentMode(request.paymentMode());
		order.setDeliveryAddress(request.deliveryAddress());
		order.setOrderStatus("CONFIRMED");
		order.setPaymentStatus("PAYMENT_PENDING");
		order.setIdempotencyKey(idempotencyKey);
		order.setUpdatedAt(Instant.now());
		OrderEntity saved = repository.save(order);

		rabbitTemplate.convertAndSend("cart.exchange", "order.confirmed", new OrderConfirmedEvent(saved.getId(),
				saved.getCustomerId(), saved.getProductId(), saved.getQuantity(), saved.getAmount(), Instant.now()));
		return saved;

	}

	public OrderEntity findById(String id) {
		return repository.findById(id).orElseThrow(() -> new BusinessException("Order not found: " + id));
	}

	public List<OrderEntity> findByCustomer(String customerId) {
		return repository.findByCustomerId(customerId);
	}

}
