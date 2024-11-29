package com.monetize360.bookstore_app.dto;

import com.monetize360.bookstore_app.domain.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OrderDto {

    private String userEmail;
    List<BookDto> books;
    private UUID orderId;
    private float total;
}
