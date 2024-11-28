package com.monetize360.bookstore_app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monetize360.bookstore_app.domain.Order;
import com.monetize360.bookstore_app.dto.OrderDto;
import com.monetize360.bookstore_app.dto.UserDto;
import com.monetize360.bookstore_app.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

public class OrderServiceImpl implements OrderService {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public OrderDto insertOrder(OrderDto orderDTO) {
        Order order = objectMapper.convertValue(orderDTO, Order.class);
        order.setOrderID(UUID.randomUUID());
        order = orderRepository.save(order);
        return objectMapper.convertValue(order, OrderDto.class);
    }


    @Override
    public Optional<OrderDto> getOrderById(UUID id) {
        return orderRepository.findById(id).map(order -> objectMapper.convertValue(order, OrderDto.class));
    }

}
