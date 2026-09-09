package com.example.demo.utils;

import java.util.UUID;

public class OrderUtil {

    public UUID parseOrderId(String orderId) {
        System.out.println( " Order Util to validate the order id "+ orderId);
        if (orderId == null || orderId.isBlank()) {
            throw new IllegalArgumentException(
                    "Order ID cannot be empty"
            );
        }

        try {
            return UUID.fromString(orderId.trim());
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(
                    "Invalid order ID. Expected a UUID, but received: " + orderId
            );
        }
    }
}
