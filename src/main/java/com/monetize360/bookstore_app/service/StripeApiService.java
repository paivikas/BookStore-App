package com.monetize360.bookstore_app.service;

import com.monetize360.bookstore_app.domain.Payment;
import com.monetize360.bookstore_app.repositories.PaymentRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentConfirmParams;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
public class StripeApiService {

    private final PaymentRepository paymentRepository;

    @Value("${stripe.api.key}")
    private String stripeApiKey;

    @Autowired
    public StripeApiService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
        Stripe.apiKey = stripeApiKey;
    }

    public PaymentIntent createPaymentIntent(long amount, String currency, UUID orderId) throws StripeException {

            // Build the PaymentIntent parameters
            PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                    .setAmount(amount)
                    .setCurrency(currency)
                    .addPaymentMethodType("card")  // Correct way to add payment method type
                    .build();

            // Create the PaymentIntent via Stripe API
            PaymentIntent paymentIntent = PaymentIntent.create(params);

            // Save the payment details to your database
            Payment payment = Payment.builder()
                    .paymentIntentId(paymentIntent.getId())
                    .orderId(orderId)
                    .paymentStatus(paymentIntent.getStatus())
                    .amount(amount)
                    .currency(currency)
                    .paymentMethodId(paymentIntent.getPaymentMethod())
                    .createdAt(paymentIntent.getCreated().toString())
                    .build();

            paymentRepository.save(payment);  // Save payment info to database

            return paymentIntent;


    }

    public PaymentIntent confirmPaymentIntent(String paymentIntentId, String paymentMethodId) throws StripeException {

            // Retrieve the PaymentIntent using its ID
            PaymentIntent paymentIntent = PaymentIntent.retrieve(paymentIntentId);

            // Build the confirmation parameters using PaymentIntentConfirmParams
            PaymentIntentConfirmParams params = PaymentIntentConfirmParams.builder()
                    .setPaymentMethod(paymentMethodId)
                    .build();

            // Confirm the PaymentIntent with the provided payment method
            paymentIntent.confirm(params);

            return paymentIntent;

    }

    public String handlePaymentSuccess(PaymentIntent paymentIntent) {
        if ("succeeded".equals(paymentIntent.getStatus())) {
            return "Payment successful! Payment ID: " + paymentIntent.getId();
        } else {
            return "Payment failed!";
        }
    }

    private PaymentIntentCreateParams buildPaymentIntentParams(long amount, String currency) {
        return PaymentIntentCreateParams.builder()
                .setAmount(amount)
                .setCurrency(currency)
                .addPaymentMethodType("card")  // Only "card" is added here, can be extended if needed.
                .build();
    }

    private PaymentIntentCreateParams buildConfirmPaymentIntentParams(String paymentMethodId) {
        return PaymentIntentCreateParams.builder()
                .setPaymentMethod(paymentMethodId)
                .build();
    }

    private Payment createPayment(PaymentIntent paymentIntent, long amount, String currency, UUID orderId) {
        return Payment.builder()
                .paymentIntentId(paymentIntent.getId())
                .orderId(orderId)
                .paymentStatus(paymentIntent.getStatus())
                .amount(amount)
                .currency(currency)
                .paymentMethodId(paymentIntent.getPaymentMethod())
                .createdAt(paymentIntent.getCreated().toString())
                .build();
    }
}
