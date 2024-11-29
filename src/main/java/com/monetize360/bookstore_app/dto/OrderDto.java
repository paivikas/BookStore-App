package com.monetize360.bookstore_app.dto;

import com.monetize360.bookstore_app.domain.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OrderDto {

    private UUID orderId;
    private String userEmail;
    private List<BookDto> books;
    private float total;
}
