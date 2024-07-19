package com.services.payment_ms.repositories;

import com.services.payment_ms.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
