package com.example.demo.orderservice.AI.Tool;

import com.example.demo.orderservice.bean.Order;
import com.example.demo.orderservice.service.OrderService;
import com.example.demo.utils.OrderUtil;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class OrderTool {

    @Autowired
    OrderService orderService;

    @Tool(
            description = """
        Retrieves information about a specific customer order.

        Use this tool when the user asks about:
        - order status
        - whether an order was successful
        - order method
        - order details
        - information about an existing order

        The orderId must be the actual order ID provided by the user
        or remembered from the conversation.

        The orderId must be a valid UUID.
        Never use placeholder values such as <ORDER_ID>, ORDER_ID,
        "order id", or any other placeholder.

        If the user has not provided an order ID and one cannot be
        obtained from the conversation, ask the user for the order ID.
        """
    )
    public Order getOrderInfo(
            @ToolParam(
                    description = """
                The actual customer order ID.
                Must be a valid UUID, for example:
                550e8400-e29b-41d4-a716-446655440000
            """
            )
                    String orderId) {

        System.out.println(
                "ORDER TOOL went to determine the Order information for the order "
                        + orderId
        );

        OrderUtil orderUtil = new OrderUtil();

        var uuid = orderUtil.parseOrderId(orderId);
// ignore the validation to test memory first , as we alredy validated the order id- its mandatory.

        Authentication authentication =
                SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        String username = authentication.getName();

        System.out.println("UserName inside AI enabled order info "+ username);
        String order = orderService.getOrderInfo(orderId, username);

        System.out.println("UserName inside AI enabled order info "+ order);

        return orderService.getOrderInfo(orderId);
    }
}
