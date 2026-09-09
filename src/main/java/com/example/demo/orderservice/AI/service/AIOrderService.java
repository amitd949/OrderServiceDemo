package com.example.demo.orderservice.AI.service;

import com.example.demo.orderservice.bean.Order;
import com.example.demo.orderservice.dao.OrderRepository;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AIOrderService {

    private final ChatClient chatClient;
    private final OrderRepository orderRepository;

    public AIOrderService(ChatClient.Builder chatClientBuilder,
                          OrderRepository orderRepository) {

        this.chatClient = chatClientBuilder.build();
        this.orderRepository = orderRepository;
    }

    public String askAboutOrders(String question) {

        List<Order> orders = orderRepository.findAll();

        String orderData = orders.toString();

        return chatClient
                .prompt()
                .user("""
                        You are an assistant for an Order Management System.

                        Here is the order data:

                        %s

                        Answer the following question using only the order data provided above.

                        Question:
                        %s
                        """.formatted(orderData, question))
                .tools()
                .call() // call to AI model
                .content();// extract the result
    }

}
