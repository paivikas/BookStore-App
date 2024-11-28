package com.monetize360.bookstore_app.repositories;
import com.monetize360.bookstore_app.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
public interface BookRepository extends JpaRepository<Book, UUID> {}