package com.example.demo.orderservice.bean;

import java.util.Date;

public class OrderResponseDTO {
    private String orderId;
    private  String name;
    private int qty;
    private  int price;
    private Date orderDate;

    @Override
    public String toString() {
        return "OrderResponseDTO{" +
                "orderId='" + orderId + '\'' +
                ", name='" + name + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                ", orderDate=" + orderDate +
                '}';
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }
}
