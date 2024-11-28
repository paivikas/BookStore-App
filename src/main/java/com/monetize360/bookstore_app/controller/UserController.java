package com.monetize360.bookstore_app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monetize360.bookstore_app.dto.UserDto;
import com.monetize360.bookstore_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping("/add")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDTO) {
        UserDto newUser = userService.insertUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PutMapping("/update")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDTO) {
        UserDto updatedUser = userService.updateUser(userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<UserDto>> getUser(@PathVariable("id") UUID id) {
        Optional<UserDto> userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Optional<UserDto>> deleteUser(@PathVariable("id") UUID id) {
        userService.deleteUser(id);
        Optional<UserDto> userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }

}
