package com.demo.spring;

import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;

@Service
public class ReactiveService {

    private final DatabaseClient databaseClient;
    private final ReactiveGateway gateway;

    public ReactiveService(DatabaseClient databaseClient, ReactiveGateway gateway) {
        this.databaseClient = databaseClient;
        this.gateway = gateway;
    }

    public Flux<String> retrieveAndProcessData() {
        Flux<String> dataFlux = databaseClient.sql("SELECT name FROM EMP")
                .map((row, metadata) -> row.get("name", String.class))
                .all();

        return gateway.processData(dataFlux);
    }
}
