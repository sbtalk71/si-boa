package com.demo.spring.ex2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.integration.dsl.IntegrationFlow;

@Configuration
@DependsOn("orderAppConfig")
public class MySubFlowsConfig {


	@Bean
	public IntegrationFlow electronicsFlow() {
		return IntegrationFlow.from("electronicsChannel").handle(System.out::println).get();
	}
	
	@Bean
	public IntegrationFlow furnitureFlow() {
		return IntegrationFlow.from("furnitureChannel").handle(System.out::println).get();
	}
	
	@Bean
	public IntegrationFlow apparelsFlow() {
		return IntegrationFlow.from("apparelsChannel").handle(System.out::println).get();
	}
}
