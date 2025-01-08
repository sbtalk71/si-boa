package com.demo.spring.aggr;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;

@Configuration
@EnableIntegration
public class SplitterAppConfig {

	@Bean
	public MessageChannel inputChannel() {
		return new DirectChannel();
	}

	@Bean
	public PollableChannel outputChannel() {
		return new QueueChannel();
	}

	@Bean
	public IntegrationFlow splittingFlow() {
		return IntegrationFlow
				.from("inputChannel")
				.split("splitterAppConfig","splitMessage")
				.aggregate(new AggregateProcessor())
				.channel("outputChannel")
				.get();
	}
	
	public List<String> splitMessage(String message) {
		System.out.println(message);
		String[] parts = message.split(" ");
		System.out.println("Splitting message..."+parts.length);
		
		return Arrays.asList(parts);
	}
	
	
	
}
