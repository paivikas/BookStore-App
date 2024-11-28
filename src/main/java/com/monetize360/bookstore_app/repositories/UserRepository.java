package com.monetize360.bookstore_app.repositories;
import com.monetize360.bookstore_app.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
public interface UserRepository extends JpaRepository<User, UUID> {}