package com.example.demo.paymentservice.service;

import com.example.demo.orderservice.bean.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.paymentservice.beans.Payments;
import com.example.demo.paymentservice.repository.PaymentRepositoty;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepositoty paymentRepositoty;

    public Payments savePayInfo(Payments payments) {
        payments.setTransId(UUID.randomUUID().toString());
        System.out.println(" Payment info is also saving "+ payments);
        return paymentRepositoty.save(payments);
    }

    public List<Payments> getPayInfo() {

        return paymentRepositoty.findAll();
    }

    public Payments getPaymentInfo(String orderId) {
        return paymentRepositoty.findByOrderId(orderId);
    }

    public Payments getPaymentInfo(String orderId, String userName) {
        return paymentRepositoty.findByOrderIdAndTransBy(orderId, userName);
    }
}
