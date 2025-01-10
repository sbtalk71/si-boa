package com.demo.spring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class LoanBrokerService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private LoanDecisionRepository repository;

    @ServiceActivator(inputChannel = "loanRequestsChannel",outputChannel = "decisionRouterChannel")
    public LoanRequest processLoanRequest(String filePath) throws IOException {
        String fileContent = Files.readString(Path.of(filePath));
        String[] parts = fileContent.split(",");
        String customerName = parts[0];
        double loanAmount = Double.parseDouble(parts[1]);

        boolean bankVerified = restTemplate.getForObject(
            "http://localhost:8080/verifyBank?customerName=" + customerName, Boolean.class);
       
        int creditScore = restTemplate.getForObject(
            "http://localhost:8080/getCreditScore?customerName=" + customerName, Integer.class);

        LoanRequest request = new LoanRequest();
        request.setCustomerName(customerName);
        request.setLoanAmount(loanAmount);

        if (bankVerified && creditScore > 700) {
            request.setApproved(true);
        } else {
            request.setApproved(false);
            request.setReason("Bank verification failed or low credit score");
        }

        return request;
    }

    @ServiceActivator(inputChannel = "approvalChannel")
    public void approveLoan(LoanRequest request) {
        LoanDecision decision = new LoanDecision();
        decision.setCustomerName(request.getCustomerName());
        decision.setApproved(true);
        repository.save(decision);
    }

    @ServiceActivator(inputChannel = "rejectionChannel")
    public void rejectLoan(LoanRequest request) {
        LoanDecision decision = new LoanDecision();
        decision.setCustomerName(request.getCustomerName());
        decision.setApproved(false);
        decision.setReason(request.getReason());
        repository.save(decision);
    }
}
