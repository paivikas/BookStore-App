package com.monetize360.bookstore_app.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class BookDto {

    private UUID bookId;
    private String name;
    private float price;
    private boolean deleted;
}
