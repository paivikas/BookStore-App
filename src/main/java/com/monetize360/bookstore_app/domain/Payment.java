package com.monetize360.bookstore_app.domain;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "payments")  // Table to store payment information
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID paymentId;  // Unique identifier for each payment

    @Column(name = "payment_intent_id")
    private String paymentIntentId;  // Stripe PaymentIntent ID

    @Column(name = "order_id")
    private UUID orderId;  // The associated order ID from your Orders table

    @Column(name = "payment_status")
    private String paymentStatus;  // Payment status (e.g., "succeeded", "failed")

    @Column(name = "amount")
    private long amount;  // Payment amount

    @Column(name = "currency")
    private String currency;  // Currency (e.g., "usd")

    @Column(name = "payment_method_id")
    private String paymentMethodId;  // Payment method used (e.g., card)

    @Column(name = "created_at")
    private String createdAt;  // Timestamp for when the payment was created
}


