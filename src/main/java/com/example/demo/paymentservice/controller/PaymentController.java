package com.example.demo.paymentservice.controller;

import com.example.demo.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.paymentservice.beans.Payments;

import java.util.List;

@RestController
@RequestMapping("/payment-provider")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @GetMapping("/paynow/{price}")
    public String payNow(@PathVariable int price) {
        return "price is  " + price;

    }

    @PostMapping("/dopayment")
    public Payments doPayment(@RequestBody Payments payments) {
        return paymentService.savePayInfo(payments);

    }
    @GetMapping("/payInfo")
    public List<Payments> payInfo() {
        return paymentService.getPayInfo();

    }
   /* @GetMapping("/getPayment/{orderId}")
    public Payments getPayment(@PathVariable String orderId) {
        return paymentService.getPaymentInfo(orderId);
    }
*/

}