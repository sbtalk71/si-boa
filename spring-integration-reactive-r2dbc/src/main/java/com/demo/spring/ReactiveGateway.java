package com.demo.spring;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import reactor.core.publisher.Flux;

@MessagingGateway
public interface ReactiveGateway {

    @Gateway(requestChannel = "inputChannel", replyChannel = "outputChannel")
    Flux<String> processData(Flux<String> data);
}
