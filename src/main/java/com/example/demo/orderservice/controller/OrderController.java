package com.example.demo.orderservice.controller;

import com.example.demo.orderservice.bean.Order;
import com.example.demo.orderservice.service.OrderService;
import com.example.demo.paymentservice.beans.Payments;
import com.example.demo.paymentservice.service.PaymentService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/*import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import io.swagger.annotations.ApiOperation;*/


@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    PaymentService paymentService;

    @Autowired
    private OrderService orderService;

   // @ApiOperation(value = "for booking API endpoint", nickname = "order API for orddering service")
    @PostMapping("/bookNow")
    public Order bookOrder(@RequestBody/* @Valid */Order order,  Authentication authentication) {


       // System.out.println("bookNow  order "+ order.getName()+ "bookNow  price "+ order.getPrice() + "bookNow  qty "+ order.getQty());
        System.out.println("  order initiated by  "+ order + authentication.getName() + "Role ");
        order.setOrderBy(authentication.getName());
        return orderService.saveOrders(order);
    }

    @SneakyThrows
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getorder")
    // @RateLimiter(name = "getMessageRateLimit", fallbackMethod = "getMessageFallBack")
   /* @HystrixCommand(fallbackMethod = "fallback_hello", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    })*/
    public List<Order> getOrder(Authentication authenticatio) {
        System.out.println(" inside getorder LIST** ----  "+ authenticatio.getName());
      //  Thread.sleep(3000);
        System.out.println(" inside getorder "+ orderService.getOrderList());
        return orderService.getOrderList();
    }

    @GetMapping("/getOrderby/{id}")
    @PreAuthorize("hasRole('USER')")
    public String getOrderby(@PathVariable String id, Authentication authentication) {
        System.out.println("inside getOrderby single order  :: " + id + "UserName "+ authentication.getName());
        String  order= orderService.getOrderInfo(id, authentication.getName());
        System.out.println(" order using username "+ order);
       // return orderService.getOrderInfo(id);
        return order;

    }

    @GetMapping("/getPayment/{orderId}")
    public Payments getPayment(@PathVariable String orderId, Authentication authentication) {

        System.out.println(" orrder details with payment "+ "UserName "+ authentication.getName());


        Payments paymentInfoUsinguserName = paymentService.getPaymentInfo(orderId, authentication.getName());

        System.out.println(" using user anme "+ paymentInfoUsinguserName);
        return paymentService.getPaymentInfo(orderId);
    }


    private List<Order> fallback_hello(Throwable throwable) {

        Order order = new Order();
        List<Order> arrayList = new ArrayList<>();
        arrayList.add(order);
        return arrayList;

        // return "Request fails. It takes long time to response";
    }

}

