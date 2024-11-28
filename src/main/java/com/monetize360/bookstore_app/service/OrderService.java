package com.monetize360.bookstore_app.service;

import com.monetize360.bookstore_app.dto.OrderDto;

import java.util.Optional;
import java.util.UUID;

public interface OrderService {
    OrderDto insertOrder(OrderDto orderDTO);
    OrderDto getOrderById(UUID id);
}
