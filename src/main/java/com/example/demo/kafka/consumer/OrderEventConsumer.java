package com.example.demo.kafka.consumer;

import com.example.demo.kafka.OrderCreatedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventConsumer {
    @KafkaListener(
            topics = "order-events",
            groupId = "order-service-group"
    )
    public void consume(OrderCreatedEvent event) {

        System.out.println(
                "Received order: " + event.orderId()
        );

        // payment processing
    }
}
