package com.ecommerce.notification.config;

import java.util.Queue;

import javax.naming.Binding;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import ch.qos.logback.classic.pattern.MessageConverter;

@Configuration
public class RabbitConfig {

	@Bean
	DirectExchange urbancartExchange() {
		return new DirectExchange("cart.exchange");
	}

	@Bean
	MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	Queue orderConfirmedNotificationQueue() {
		return new Queue("notification.order.confirmed.queue", true);
	}

	@Bean
	Queue shipmentCreatedNotificationQueue() {
		return new Queue("notification.shipment.created.queue", true);
	}

	@Bean
	Binding orderNotificationBinding(Queue orderConfirmedNotificationQueue, DirectExchange cartExchange) {
		return BindingBuilder.bind(orderConfirmedNotificationQueue).to(cartExchange).with("order.confirmed");
	}

	@Bean
	Binding shipmentNotificationBinding(Queue shipmentCreatedNotificationQueue, DirectExchange cartExchange) {
		return BindingBuilder.bind(shipmentCreatedNotificationQueue).to(cartExchange).with("shipment.created");
	}

}
