package com.example.fault_tolerance_demo.controller;

import com.example.fault_tolerance_demo.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private PaymentService paymentService;
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    OrderController(PaymentService paymentService){
        this.paymentService=paymentService;
    }
    @GetMapping
    public ResponseEntity<String> takeOrder() throws Exception {
        logger.info("Order received");
        String status = paymentService.callPaymentService();
        logger.info("payment received");
        return ResponseEntity.ok(status);
    }
}
