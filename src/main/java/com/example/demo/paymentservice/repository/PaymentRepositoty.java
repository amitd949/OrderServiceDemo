package com.example.demo.paymentservice.repository;

import com.example.demo.paymentservice.beans.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepositoty extends JpaRepository<Payments, Integer> {
  Payments findByOrderId(String orderId);
  Payments findByOrderIdAndTransBy(String orderId, String transBy);
}
