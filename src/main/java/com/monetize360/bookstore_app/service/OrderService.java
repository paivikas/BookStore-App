package com.monetize360.bookstore_app.service;

import com.monetize360.bookstore_app.dto.OrderDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderService {
    OrderDto insertOrder(OrderDto orderDTO);
    List<OrderDto> getOrderByEmail(String email);
}
