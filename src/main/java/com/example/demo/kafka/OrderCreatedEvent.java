package com.example.demo.kafka;

public record  OrderCreatedEvent( String name,  int qty,   double price,  String orderId) {
}
