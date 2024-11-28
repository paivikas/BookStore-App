package com.monetize360.bookstore_app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monetize360.bookstore_app.domain.Book;
import com.monetize360.bookstore_app.dto.BookDto;
import com.monetize360.bookstore_app.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService{
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public BookDto insertBook(BookDto bookDTO) {
        Book book = objectMapper.convertValue(bookDTO, Book.class);
        book.setBookId(UUID.randomUUID());
        book = bookRepository.save(book);
        return objectMapper.convertValue(book, BookDto.class);
    }

    @Override
    public BookDto updateBook(BookDto bookDTO) {
        Book book = bookRepository.findById(bookDTO.getBookId()).orElseThrow(() -> new RuntimeException("User not found"));
        book.setName(bookDTO.getName());
        book.setPrice(bookDTO.getPrice());
        Book updatedBook = bookRepository.save(book);
        return objectMapper.convertValue(updatedBook, BookDto.class);
    }

    @Override
    public Optional<BookDto> getBookById(UUID id) {
        return bookRepository.findById(id).map(book -> objectMapper.convertValue(book, BookDto.class));
    }

    @Override
    public void deleteBook(UUID id) {
        Optional<Book> optionalUser = bookRepository.findById(id);
        if (optionalUser.isPresent()) {
            Book book = optionalUser.get();
            book.setDeleted(true);
            bookRepository.save(book);
        } else {
            throw new RuntimeException("Contact not found");
        }
    }
}
