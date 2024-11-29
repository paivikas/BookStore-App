package com.monetize360.bookstore_app.controller;

import com.monetize360.bookstore_app.dto.OrderDto;
import com.monetize360.bookstore_app.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity<OrderDto> addContact(@RequestBody OrderDto OrderDto){

        OrderDto createdOrder = orderService.insertOrder(OrderDto);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);

    }



    @GetMapping("/get/{email}")
    public ResponseEntity<List<OrderDto>> getContact(@PathVariable("email") String email){

        List<OrderDto> orders = orderService.getOrderByEmail(email);
        if(orders!=null) {
            return new ResponseEntity<>(orders, HttpStatus.FOUND);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}

