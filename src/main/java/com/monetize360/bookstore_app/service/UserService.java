package com.monetize360.bookstore_app.service;

import com.monetize360.bookstore_app.dto.UserDto;

import java.util.Optional;
import java.util.UUID;

public interface UserService {
    UserDto insertUser(UserDto contactDTO);
    UserDto updateUser(UserDto contactDTO);
    Optional<UserDto> getUserById(UUID id);
    void deleteUser(UUID id);
}
