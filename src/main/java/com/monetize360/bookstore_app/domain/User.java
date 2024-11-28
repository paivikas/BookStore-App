package com.monetize360.bookstore_app.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class User {
    private UUID userID;
    private String name;
    private String password;
    private String email;
    private boolean deleted;
}
