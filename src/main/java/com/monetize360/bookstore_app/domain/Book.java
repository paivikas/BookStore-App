package com.monetize360.bookstore_app.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Book {
    private UUID bookId;
    private String name;
    private float price;
}
