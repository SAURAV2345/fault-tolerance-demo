package com.example.fault_tolerance_demo.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PaymentService {
    private final Random random = new Random();
    public String callPaymentService() throws Exception {
        int rand = random.nextInt(10);
        int delay = 500 + random.nextInt(1500);
        Thread.sleep(delay);

        if(rand<3){
            throw  new Exception("Payment service failed");
        }

        return "Payment processed in "+ delay + "ms";
    }
}
