package com.demo.spring;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface LoanBrokerGateway {
    @Gateway(requestChannel = "loanRequestsChannel")
    void processLoanRequest(LoanRequest request);
}
