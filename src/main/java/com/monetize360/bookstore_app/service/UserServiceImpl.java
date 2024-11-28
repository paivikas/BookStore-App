package com.monetize360.bookstore_app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monetize360.bookstore_app.domain.Users;
import com.monetize360.bookstore_app.dto.UserDto;
import com.monetize360.bookstore_app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.UUID;

public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public UserDto insertUser(UserDto userDTO) {
        Users users = objectMapper.convertValue(userDTO, Users.class);
        users.setUserId(UUID.randomUUID());
        users = userRepository.save(users);
        return objectMapper.convertValue(users, UserDto.class);

    }

    @Override
    public UserDto updateUser(UserDto userDTO) {
        Users users = userRepository.findById(userDTO.getUserID()).orElseThrow(() -> new RuntimeException("Users not found"));
        users.setName(userDTO.getName());
        users.setEmail(userDTO.getEmail());
        users.setPassword(userDTO.getPassword());
        users.setDeleted(userDTO.isDeleted());
        Users updatedUsers = userRepository.save(users);
        return objectMapper.convertValue(updatedUsers, UserDto.class);

    }

    @Override
    public Optional<UserDto> getUserById(UUID id) {
        return userRepository.findById(id).map(contact -> objectMapper.convertValue(contact, UserDto.class));
    }

    @Override
    public void deleteUser(UUID id) {
        Optional<Users> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            Users users = optionalUser.get();
            users.setDeleted(true);
            userRepository.save(users);
        } else {
            throw new RuntimeException("Contact not found");
        }
    }
}
