package com.example.demo.orderservice.AI.Tool;

import com.example.demo.paymentservice.beans.PaymentResponseDTO;
import com.example.demo.paymentservice.beans.Payments;
import com.example.demo.paymentservice.service.PaymentService;
import com.example.demo.utils.OrderUtil;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentTool {

    @Autowired
    PaymentService paymentService;


    @Tool(description = """
        Retrieves payment information for an order.
        Use this tool when the user asks about payment status,
        payment success, payment method, or payment details.
        """)
    public PaymentResponseDTO getPaymentInfo(  @ToolParam(description = "The order ID") String orderId) {
        System.out.println(" PayMent Tool went to determine the payment information for the order "+ orderId);
        var payments=  paymentService.getPaymentInfo(orderId);

        OrderUtil orderUtil = new OrderUtil();
        var uuid = orderUtil.parseOrderId(orderId);
        
        return new PaymentResponseDTO(
                payments.getOrderId(),
                payments.getTransId(),
                payments.getPaymentStatus(),
                payments.getTransDate()
        );

    }


}
