package com.ecommerce.payment.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.core.dto.ApiResponse;
import com.ecommerce.core.dto.PaymentRequest;
import com.ecommerce.payment.model.PaymentTransaction;
import com.ecommerce.payment.service.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/payments")
public class PaymentController {
	   private final PaymentService service;
	    public PaymentController(PaymentService service) { this.service = service; }

	    @PostMapping("/process")
	    public ApiResponse<PaymentTransaction> process(@Valid @RequestBody PaymentRequest request) {
	        return ApiResponse.success("Payment processed", service.process(request));
	    }
}
