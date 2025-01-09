package com.demo.spring;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationController {

    @GetMapping("/verifyBank")
    public boolean verifyBank(@RequestParam String customerName) {
        // Simulate bank verification
        return "valid-customer".equalsIgnoreCase(customerName);
    }

    @GetMapping("/getCreditScore")
    public int getCreditScore(@RequestParam String customerName) {
        // Simulate credit score lookup
        return "valid-customer".equalsIgnoreCase(customerName) ? 750 : 600;
    }
}
