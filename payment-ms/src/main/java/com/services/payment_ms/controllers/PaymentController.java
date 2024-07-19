package com.services.payment_ms.controllers;

import com.services.payment_ms.dto.PaymentDTO;
import com.services.payment_ms.entities.enuns.PaymentMethod;
import com.services.payment_ms.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService service;

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDTO> findPaymentById(@PathVariable Long id){
        return ResponseEntity.ok(service.findPaymentById(id));
    }

    @PutMapping("/pay/{paymentId}")
    public ResponseEntity<PaymentDTO> pay(@PathVariable Long paymentId, @RequestBody PaymentMethod method){
        return ResponseEntity.ok(service.pay(paymentId, method));
    }
}
