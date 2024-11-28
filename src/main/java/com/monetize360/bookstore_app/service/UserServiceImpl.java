package com.monetize360.bookstore_app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monetize360.bookstore_app.domain.User;
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
        User user = objectMapper.convertValue(userDTO, User.class);
        user.setUserID(UUID.randomUUID());
        user = userRepository.save(user);
        return objectMapper.convertValue(user, UserDto.class);

    }

    @Override
    public UserDto updateUser(UserDto userDTO) {
        User user = userRepository.findById(userDTO.getUserID()).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setDeleted(userDTO.isDeleted());
        User updatedUser = userRepository.save(user);
        return objectMapper.convertValue(updatedUser, UserDto.class);

    }

    @Override
    public Optional<UserDto> getUserById(UUID id) {
        return userRepository.findById(id).map(contact -> objectMapper.convertValue(contact, UserDto.class));
    }

    @Override
    public void deleteUser(UUID id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setDeleted(true);
            userRepository.save(user);
        } else {
            throw new RuntimeException("Contact not found");
        }
    }
}
