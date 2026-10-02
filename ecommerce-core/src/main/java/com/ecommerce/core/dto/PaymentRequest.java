package com.ecommerce.core.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PaymentRequest(@NotBlank String orderId, @NotNull BigDecimal amount, @NotBlank String paymentMode) {

}
