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

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public OrderDto insertOrder(OrderDto orderDto) {
        Orders order = objectMapper.convertValue(orderDto, Orders.class);

        // Optional: Use bookRepository to check if the book exists in the database before saving it
        if (orderDto.getBooks() != null) {
            List<Book> books = orderDto.getBooks().stream()
                    .map(bookDto -> {
                        Book book = objectMapper.convertValue(bookDto, Book.class);
                        book.setOrders(order); // Set the order reference

                        // Check if book exists in the database (example)
                        Optional<Book> existingBook = bookRepository.findById(book.getBookId());
                        if (existingBook.isPresent()) {
                            book = existingBook.get(); // Use the existing book if found
                        }

                        return book;
                    })
                    .toList();
            order.setBooks(books); // Set the books in the order
        }

        // Calculate the total price and set it on the order
        order.setTotal(calculateTotalPrice(orderDto));

        // Save the order in the repository
        Orders savedOrder = orderRepository.save(order);

        // Convert saved order back to OrderDto and return it
        return objectMapper.convertValue(savedOrder, OrderDto.class);
    }


    public float calculateTotalPrice(OrderDto orderDTO) {
        return  orderDTO.getBooks()
                .stream()
                .map(BookDto::getPrice)
                .reduce(0.0f, Float::sum);
    }

    @Override
    public List<OrderDto> getOrderByEmail(String email) {
        List<Orders> orders = orderRepository.findByUserEmail(email);
        return orders.stream()
                .map(order -> objectMapper.convertValue(order, OrderDto.class))
                .toList();
    }


}
