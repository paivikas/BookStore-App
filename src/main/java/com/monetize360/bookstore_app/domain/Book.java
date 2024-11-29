package com.monetize360.bookstore_app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID bookId;
    @Column(name = "name")
    private String name;
    @Column(name = "price")
    private float price;
    @Column(name = "deleted")
    private boolean deleted;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "orderId")
    private Orders orders;
}
