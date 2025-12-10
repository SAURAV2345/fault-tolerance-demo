package com.example.fault_tolerance_demo.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.Executors;

@Service
public class PaymentService {
    private final Random random = new Random();

    @CircuitBreaker(name = "paymentService",fallbackMethod = "fallbackPayment")
    public String callPaymentService() throws Exception {
        int rand = random.nextInt(10);
        int delay = 500 + random.nextInt(1500);
        Thread.sleep(delay);

        if(rand<7){
            throw  new Exception("Payment service failed");
        }

        return "Payment processed in "+ delay + "ms";
    }
    public String fallbackPayment(Exception e){
        return "Payment service unavailable" + e.getMessage();
    }
}
