package com.ecommerce.order.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
	@Bean
    DirectExchange urbancartExchange() { return new DirectExchange("cart.exchange"); }

    @Bean
    MessageConverter messageConverter() { return new Jackson2JsonMessageConverter(); }
}
