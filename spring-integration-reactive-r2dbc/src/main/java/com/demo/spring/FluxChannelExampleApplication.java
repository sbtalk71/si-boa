package com.demo.spring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FluxChannelExampleApplication implements CommandLineRunner {

    private final ReactiveService reactiveService;

    public FluxChannelExampleApplication(ReactiveService reactiveService) {
        this.reactiveService = reactiveService;
    }

    public static void main(String[] args) {
        SpringApplication.run(FluxChannelExampleApplication.class, args);
    }

    @Override
    public void run(String... args) {
        reactiveService.retrieveAndProcessData()
                .doOnNext(System.out::println)
                .blockLast();
    }
}
