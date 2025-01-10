package com.demo.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.config.GlobalChannelInterceptor;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.messaging.MessageChannel;
import org.springframework.security.authorization.AuthorityAuthorizationManager;
import org.springframework.security.messaging.access.intercept.AuthorizationChannelInterceptor;

@Configuration
@EnableIntegration
public class SecuredIntegrationConfig {

	@Bean
	public MessageChannel securedChannel() {
		return new DirectChannel();
	}

	@Bean
	@GlobalChannelInterceptor(patterns = "secured*")
	AuthorizationChannelInterceptor authorizationChannelInterceptor() {
		return new AuthorizationChannelInterceptor(AuthorityAuthorizationManager.hasAnyRole("ADMIN", "USER"));
	}

	@Bean
	public IntegrationFlow flow() {
		return IntegrationFlow
				.from("securedChannel")
				.handle(System.out::println)
				.get();
	}

}
