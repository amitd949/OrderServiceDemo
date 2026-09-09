package com.example.demo.orderservice.AI.controller;

import com.example.demo.orderservice.AI.Tool.OrderTool;
import com.example.demo.orderservice.AI.Tool.PaymentTool;
import com.example.demo.orderservice.AI.service.AIOrderService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/ai")
public class AIController {

    private final ChatClient chatClient;

    @Autowired AIOrderService aiOrderService;

    @Autowired
    OrderTool orderTool;

    @Autowired
    PaymentTool paymentTool;

    private  final ChatMemory chatMemory;

    private final MessageChatMemoryAdvisor memoryAdvisor;




    public AIController(ChatClient.Builder chatClientBuilder, ChatMemory chatMemory) {
        this.chatClient = chatClientBuilder.build();
        this.chatMemory= chatMemory;

        this.memoryAdvisor =
                MessageChatMemoryAdvisor.builder( this.chatMemory)
                        .build();
        System.out.println("ChatMemory = " +  this.chatMemory);
    }




    @GetMapping("/ask")
    public String ask(@RequestParam String question) {

        return chatClient
                .prompt()
                .user(question)
                .call()
                .content();
    }

    @GetMapping("/askNew")
    public String askNew(@RequestParam String question, Authentication authentication,  @RequestParam(required = false) String conversationId) {
        System.out.println( " inside askNew having order tool "+ question +" user "+ authentication.getName() + " for the chat history check id  " +  conversationId);


        if(conversationId== null ){
            conversationId = UUID.randomUUID().toString();
        }

        System.out.println(" after generating for the user "+ conversationId);
        String finalConversationId = conversationId;
        return chatClient
                .prompt()
                .user(question)
                .advisors(memoryAdvisor)
                .advisors(advisor -> advisor.param(
                        ChatMemory.CONVERSATION_ID,
                        finalConversationId
                ))
                .tools(orderTool, paymentTool)
                .call()
                .content();
    }

    @GetMapping("/askOrder")
    public String askOrder(@RequestParam String question) {

        String response = aiOrderService.askAboutOrders(question);
        System.out.println(" final response using OLLAMA AI "+ response);
        return response;
    }


    @PostMapping("/conversation")
    public String createConversation(Authentication authentication) {

        String conversationId = UUID.randomUUID().toString();

        System.out.println(
                "New conversation: " + conversationId +
                        " user: " + authentication.getName()
        );

        return conversationId;
    }



}
