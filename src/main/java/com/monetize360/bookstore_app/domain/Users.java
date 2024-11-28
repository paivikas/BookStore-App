package com.monetize360.bookstore_app.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID userId;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "deleted")
    private boolean deleted;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Orders> orders;
}
