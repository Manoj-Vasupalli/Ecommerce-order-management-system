package com.ecommerce.core.event;

import java.time.Instant;

public record PaymentCompletedEvent(String orderId, String paymentStatus, String paymentReference, Instant completedAt) {

}
