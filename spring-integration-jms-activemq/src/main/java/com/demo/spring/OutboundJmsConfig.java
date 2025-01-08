package com.demo.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.jms.dsl.Jms;
import org.springframework.messaging.MessageChannel;

import jakarta.jms.ConnectionFactory;

@Configuration
@EnableIntegration
public class OutboundJmsConfig {

	@Autowired
	ConnectionFactory cf;
	@Bean
	public MessageChannel inputChannel() {
		return new DirectChannel();
	}
	
	@Bean
	public IntegrationFlow jmsFlow() {
		return IntegrationFlow
				.from("inputChannel")
				.handle(Jms.outboundAdapter(cf).destination("myQueue"))
				.get();
	}
}
