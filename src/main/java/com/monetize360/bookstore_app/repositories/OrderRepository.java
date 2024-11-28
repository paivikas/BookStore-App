package com.monetize360.bookstore_app.repositories;

import com.monetize360.bookstore_app.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {

}
