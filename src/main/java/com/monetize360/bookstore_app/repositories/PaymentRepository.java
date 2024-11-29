package com.monetize360.bookstore_app.repositories;

import com.monetize360.bookstore_app.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
}
