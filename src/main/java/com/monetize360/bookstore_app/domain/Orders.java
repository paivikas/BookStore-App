package com.monetize360.bookstore_app.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Integer orderId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private Users users;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<Book> books;

    @Column(name = "totalamount")
    private float total;
}
