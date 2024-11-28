package com.monetize360.bookstore_app.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class OrderDto {

    private UUID userID;
    private UUID bookID;
    private UUID orderID;
    private float total;
}
