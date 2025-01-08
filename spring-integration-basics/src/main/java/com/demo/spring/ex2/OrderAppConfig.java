package com.demo.spring.ex2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.messaging.MessageChannel;

@Configuration
@EnableIntegration
public class OrderAppConfig {

	@Bean
	public MessageChannel inputChannel() {
		return new DirectChannel();
	}

	@Bean
	public MessageChannel electronicsChannel() {
		return new DirectChannel();
	}

	@Bean
	public MessageChannel furnitureChannel() {
		return new DirectChannel();
	}

	@Bean
	public MessageChannel apparelsChannel() {
		return new DirectChannel();
	}

	@Bean
	public IntegrationFlow orderAppFlow() {
		return IntegrationFlow.from("inputChannel")
				.route(Order::getCategory, mapping -> mapping.channelMapping("electronics", "electronicsChannel")
						.channelMapping("furniture", "furnitureChannel")
						.channelMapping("apparel", "apparelsChannel"))
				
				.get();

	}

	
	public String routeToChannel(Order order) {
		String category = order.getCategory();
		if (category.equals("electronics")) {
			return "electronicsChannel";
		} else if (category.equals("furniture")) {
			return "furnitureChannel";

		} else {
			return "apparelsChannel";
		}
	}

}
