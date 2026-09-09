package com.example.demo.orderservice.dao;

import com.example.demo.orderservice.bean.Order;
import com.example.demo.paymentservice.beans.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Order findByOrderId(String orderId);
    Order findByOrderIdAndOrderBy(String orderId, String orderBy);
}
