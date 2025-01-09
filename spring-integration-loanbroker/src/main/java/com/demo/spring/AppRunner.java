package com.demo.spring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class AppRunner implements CommandLineRunner {

    @Autowired
    private LoanBrokerGateway gateway;

    @Override
    public void run(String... args) throws Exception {
        LoanRequest request = new LoanRequest();
        request.setCustomerName("valid-customer");
        request.setLoanAmount(10000);

        gateway.processLoanRequest(request);
    }
}
