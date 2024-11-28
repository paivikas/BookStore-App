package com.monetize360.bookstore_app.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDto {
    private UUID userID;
    private String name;
    private String password;
    private String email;
}
