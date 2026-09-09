package com.example.demo.orderservice.service;

import com.example.demo.orderservice.bean.Order;
import com.example.demo.orderservice.dao.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.demo.paymentservice.beans.Payments;
import com.example.demo.paymentservice.service.PaymentService;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
   private PaymentService paymentService;

    public Order saveOrders(Order order) {

        String orderId = String.valueOf(UUID.randomUUID());
        order.setOrderId(orderId);
        order.setOrderDate(new Date());
        // save payment info as well.
        // create payment bean
        Payments payments = new Payments();
        payments.setOrderId(orderId);
        payments.setPaymentStatus("SuCCESS");
        payments.setTransDate(new Date());
        payments.setTransBy(order.getOrderBy());
        System.out.println(" Payment info is also saving "+ payments);
        paymentService.savePayInfo(payments);
        return orderRepository.save(order);
    }

    public List<Order> getOrderList() {

        return orderRepository.findAll();
    }


    public Order getOrderInfo(String orderId) {
         Order order=  orderRepository.findByOrderId(orderId);
            return order;

    }

    public String getOrderInfo(String orderId, String userName) {
        Order order=  orderRepository.findByOrderIdAndOrderBy(orderId, userName);
        if(order== null){
             List<Order> orderList= getOrderList();
            System.out.println(" orderList "+ orderList );
            order=  orderList.stream().filter(order1 ->  order1.getOrderId().equalsIgnoreCase(orderId) && order1.getName().equalsIgnoreCase(userName))
                    .findFirst().orElseThrow(() ->
                            new ResponseStatusException(
                                    HttpStatus.NOT_FOUND,
                                    "Order not found for given "+ orderId + " for user "+ userName
                            ));
        }


        return  order.toString();



    }

}
