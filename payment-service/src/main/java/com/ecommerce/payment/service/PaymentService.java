package com.ecommerce.payment.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.ecommerce.core.dto.PaymentRequest;
import com.ecommerce.core.event.PaymentCompletedEvent;
import com.ecommerce.payment.model.PaymentTransaction;
import com.ecommerce.payment.repo.PaymentRepository;

@Service
public class PaymentService {
	
	
	    private final PaymentRepository repository;
	    private final RabbitTemplate rabbitTemplate;
	    public PaymentService(PaymentRepository repository, RabbitTemplate rabbitTemplate) { this.repository = repository; this.rabbitTemplate = rabbitTemplate; }

	    public PaymentTransaction process(PaymentRequest request) {
	        PaymentTransaction tx = new PaymentTransaction();
	        tx.setId("PAY-" + UUID.randomUUID());
	        tx.setOrderId(request.orderId());
	        tx.setAmount(request.amount());
	        tx.setPaymentMode(request.paymentMode());
	        tx.setPaymentStatus("SUCCESS");
	        tx.setPaymentReference("PGW-" + UUID.randomUUID().toString().substring(0, 8));
	        PaymentTransaction saved = repository.save(tx);
	        rabbitTemplate.convertAndSend("cart.exchange", "payment.completed",
	                new PaymentCompletedEvent(saved.getOrderId(), saved.getPaymentStatus(), saved.getPaymentReference(), Instant.now()));
	        return saved;
	    }
	

}
