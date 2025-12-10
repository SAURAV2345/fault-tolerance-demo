package com.example.fault_tolerance_demo.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Random;


@Service
public class PaymentService {
    private final Random random = new Random();
    private static int count=0;
    private final static Logger logger = LoggerFactory.getLogger(PaymentService.class);

    @Retry(name="paymentService")
    @CircuitBreaker(name = "paymentService")
    public String callPaymentService() throws Exception {
        logger.info("Attempt- "+count++);
        int rand = random.nextInt(10);
        int delay = 500 + random.nextInt(1500);
        Thread.sleep(delay);

        if(rand<10){
            throw  new Exception(" Payment service failed ");
        }

        return "Payment processed in "+ delay + "ms";
    }
    public String fallbackPayment(Exception e){
        return " ----Fallback called----- " + e.getMessage();
    }
}
