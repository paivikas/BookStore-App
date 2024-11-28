package com.monetize360.bookstore_app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monetize360.bookstore_app.domain.Orders;
import com.monetize360.bookstore_app.dto.BookDto;
import com.monetize360.bookstore_app.dto.OrderDto;
import com.monetize360.bookstore_app.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public OrderDto insertOrder(OrderDto orderDTO) {
        Orders order = objectMapper.convertValue(orderDTO, Orders.class);
        order.setOrderId(UUID.randomUUID());
        float total = calculateTotalPrice(orderDTO);
        order.setTotal(total);
        order = orderRepository.save(order);
        return objectMapper.convertValue(order, OrderDto.class);
    }


    public float calculateTotalPrice(OrderDto orderDTO) {
        return  orderDTO.getBooks()
                .stream()
                .map(BookDto::getPrice)
                .reduce(0.0f, Float::sum);
    }

    @Override
    public Optional<OrderDto> getOrderById(UUID id) {
        return orderRepository.findById(id).map(order -> objectMapper.convertValue(order, OrderDto.class));
    }

}
