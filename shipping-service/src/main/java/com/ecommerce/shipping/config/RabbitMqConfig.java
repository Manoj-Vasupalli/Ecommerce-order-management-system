package com.ecommerce.shipping.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.client.impl.AMQImpl.Queue;

@Configuration
public class RabbitMqConfig {
	@Bean
	DirectExchange urbancartExchange() {
		return new DirectExchange("cart.exchange");
	}

	@Bean
	MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	Queue orderConfirmedQueue() {
		return new Queue("shipping.order.confirmed.queue", true);
	}

	@Bean
	Binding shippingBinding(Queue orderConfirmedQueue, DirectExchange urbancartExchange) {
		return BindingBuilder.bind(orderConfirmedQueue).to(urbancartExchange).with("order.confirmed");
	}
}
