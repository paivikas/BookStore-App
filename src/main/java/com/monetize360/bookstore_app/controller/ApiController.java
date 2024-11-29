package com.monetize360.bookstore_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class ApiController {
    @Autowired
    private MessageChannel apiRequestChannel;

    @PostMapping("/{apiType}")
    public ResponseEntity<String> processApiRequest(
            @PathVariable("apiType") String apiType,
            @RequestBody String request) {

        Message<String> message = MessageBuilder.withPayload(request)
                .setHeader("apiType", apiType)
                .build();

        apiRequestChannel.send(message);

        // Simulate synchronous response for simplicity
        return ResponseEntity.ok("Request routed to: " + apiType);
    }
}
