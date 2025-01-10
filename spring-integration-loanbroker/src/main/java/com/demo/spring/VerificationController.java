package com.demo.spring;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;

@RestController
public class VerificationController {

    @GetMapping("/verifyBank")
    @Timed("loanbroker.bank")
    @Counted("loanbroker.bank.counts")
    public boolean verifyBank(@RequestParam String customerName) {
        // Simulate bank verification
        return "valid-customer".equalsIgnoreCase(customerName);
    }

    @GetMapping("/getCreditScore")
    @Timed("loanbroker.creditscore")
    @Counted("loanbroker.creditscore.counts")
    public int getCreditScore(@RequestParam String customerName) {
        // Simulate credit score lookup
        return "valid-customer".equalsIgnoreCase(customerName) ? 750 : 600;
    }
}
