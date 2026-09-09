package com.example.demo.orderservice.bean;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="ORDER_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private String orderId;
   // @NotBlank(message = "Name is mandatory")
    private  String name;
    //@NotBlank(message = "Qty is mandatory")
  //  @Min(value = 1, message = "Minimium Quantity is required")
  //  @Max(value = 100, message = "per customer only is allowed only ")
    private int qty;

   // @Min(value = 1, message = "Minimium price  is required")
    private  int price;

    private Date orderDate;
    private String orderBy;

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderId='" + orderId + '\'' +
                ", name='" + name + '\'' +
                ", qty=" + qty +
                ", price=" + price +
                ", orderDate=" + orderDate +
                ", orderBy='" + orderBy + '\'' +
                '}';
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = new Date();
    }
}
