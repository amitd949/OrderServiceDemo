package com.example.demo.kafka.producer;

import com.example.demo.kafka.OrderCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderEventProducer {

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public OrderEventProducer(
            KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publish(OrderCreatedEvent event) {
        System.out.println(" published into the topics "+ event);
        kafkaTemplate.send(
                "order-events",
                event.orderId(),
                event
        );
    }
}
