package com.example.demo.kafka.consumer;

import com.example.demo.kafka.OrderCreatedEvent;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderEventConsumer {
    @KafkaListener(
            topics = "order-events",
            groupId = "order-service-group"
    )
    public void consume(ConsumerRecord <String, OrderCreatedEvent> record) {

        System.out.println(
                "Partition = " + record.partition()
                        + ", Offset = " + record.offset()
                        + ", Key = " + record.key()
                        + ", Value = " + record.value()
        );

        // payment processing
    }
}
