package com.demo.spring.ex1;

import java.util.HashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.NullChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;

@Configuration
@EnableIntegration
public class IntegrationConfig {

	@Bean
	public MessageChannel inputChannel() {
		return new DirectChannel();
	}

	@Bean
	public PollableChannel outputChannel() {
		return new QueueChannel();
	}
	
	

	//@ServiceActivator(inputChannel = "inputChannel", outputChannel = "outputChannel")
	public Message<HashMap<String, String>> modifyMessage(Message<String> message) {
		HashMap<String, String> data=new HashMap<>();
		data.put("name", "Shantanu");
		data.put("message", message.getPayload());
		
		return MessageBuilder.withPayload(data).build();
	}
	
	//@ServiceActivator(inputChannel = "outputChannel")
	public void printMessage(Message<HashMap<String, String>> message) {
		//return message.getPayload().get("name");
		System.out.println(message.getPayload());
	}
	
	
	@Bean
	public IntegrationFlow appFlow() {
		return IntegrationFlow
				.from("inputChannel")
				.handle("integrationConfig","modifyMessage")
				.handle("integrationConfig","printMessage")
				//.channel("outputChannel")
				.get();
	}
	
}
