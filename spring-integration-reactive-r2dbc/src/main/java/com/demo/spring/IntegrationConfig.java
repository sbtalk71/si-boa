package com.demo.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.FluxMessageChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;

import reactor.core.publisher.Flux;

@Configuration
@EnableIntegration
public class IntegrationConfig {

    @Bean
    public FluxMessageChannel inputChannel() {
        return new FluxMessageChannel();
    }

    @Bean
    public FluxMessageChannel outputChannel() {
        return new FluxMessageChannel();
    }

    @Bean
    public IntegrationFlow integrationFlow() {
        return IntegrationFlow
                .from("inputChannel")
               .<Flux<String>, Flux<String>>transform(flux->flux.map(String::toUpperCase))
                .channel(outputChannel())
                .get();
    }
}
