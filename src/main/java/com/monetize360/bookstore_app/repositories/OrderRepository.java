package com.monetize360.bookstore_app.repositories;

import com.monetize360.bookstore_app.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Orders, UUID> {
    List<Orders> findByUserEmail(String userEmail);
}
