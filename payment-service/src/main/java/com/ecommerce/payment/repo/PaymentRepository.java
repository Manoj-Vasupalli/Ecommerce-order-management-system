package com.ecommerce.payment.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.payment.model.PaymentTransaction;
@Repository
public interface PaymentRepository extends JpaRepository<PaymentTransaction, String> {
	 Optional<PaymentTransaction> findByOrderId(String orderId);
}
