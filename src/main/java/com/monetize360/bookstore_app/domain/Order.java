package com.monetize360.bookstore_app.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Order {

    private UUID userID;
    private UUID bookID;
    private UUID orderID;
    private float total;
}
