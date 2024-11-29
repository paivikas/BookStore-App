package com.monetize360.bookstore_app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monetize360.bookstore_app.domain.Book;
import com.monetize360.bookstore_app.domain.Orders;
import com.monetize360.bookstore_app.dto.BookDto;
import com.monetize360.bookstore_app.dto.OrderDto;
import com.monetize360.bookstore_app.repositories.BookRepository;
import com.monetize360.bookstore_app.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ObjectMapper objectMapper;




    public float calculateTotalPrice(OrderDto orderDTO) {
        return  orderDTO.getBooks()
                .stream()
                .map(BookDto::getPrice)
                .reduce(0.0f, Float::sum);
    }
    @Override
    public OrderDto insertOrder(OrderDto orderDto) {
        Orders order = new Orders();
        order.setUserEmail(orderDto.getUserEmail());
        order.setTotal(calculateTotalPrice(orderDto));

        // Convert BookDto to Book entities
        List<Book> books = orderDto.getBooks().stream()
                .map(bookDto -> bookRepository.findById(bookDto.getBookId()).orElseThrow(() -> new RuntimeException("Book not found")))
                .collect(Collectors.toList());

        order.setBooks(books);

        Orders savedOrder = orderRepository.save(order);

        return mapToDto(savedOrder);
    }

    private OrderDto mapToDto(Orders order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getOrderId());
        orderDto.setUserEmail(order.getUserEmail());
        orderDto.setTotal(order.getTotal());

        List<BookDto> bookDtos = order.getBooks().stream()
                .map(book -> {
                    BookDto bookDto = new BookDto();
                    bookDto.setBookId(book.getBookId());
                    bookDto.setName(book.getName());
                    bookDto.setPrice(book.getPrice());
                    bookDto.setDeleted(book.isDeleted());
                    return bookDto;
                }).collect(Collectors.toList());

        orderDto.setBooks(bookDtos);
        return orderDto;
    }
    @Override
    public List<OrderDto> getOrderByEmail(String userEmail) {
        List<Orders> ordersList = orderRepository.findByUserEmail(userEmail);

        if (ordersList.isEmpty()) {
            throw new RuntimeException("No orders found for user: " + userEmail);
        }

        return ordersList.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }


}
