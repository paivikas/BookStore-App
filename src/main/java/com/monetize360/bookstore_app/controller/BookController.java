package com.monetize360.bookstore_app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monetize360.bookstore_app.dto.BookDto;
import com.monetize360.bookstore_app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/add")
    public ResponseEntity<BookDto> addBook(@RequestBody BookDto bookDto) {
        BookDto newBook = bookService.insertBook(bookDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newBook);
    }

    @PutMapping("/update")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto) {
        BookDto updatedBook = bookService.updateBook(bookDto);
        return ResponseEntity.ok(updatedBook);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable UUID id) {
        try {
            BookDto bookDto = bookService.getBookById(id);
            return ResponseEntity.ok(bookDto);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BookDto> deleteBook(@PathVariable("id") UUID id) {
        bookService.deleteBook(id);
        BookDto bookDto = bookService.getBookById(id);
        return ResponseEntity.ok(bookDto);
    }
}
