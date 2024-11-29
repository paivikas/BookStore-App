package com.monetize360.bookstore_app.service;

import com.monetize360.bookstore_app.dto.BookDto;

import java.util.Optional;
import java.util.UUID;

public interface BookService {
    BookDto insertBook(BookDto bookDTO);
    BookDto updateBook(BookDto bookDTO);
    BookDto getBookById(UUID id);
    void deleteBook(UUID id);
}
