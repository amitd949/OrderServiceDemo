package com.example.demo.paymentservice.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "PAYMENT_TB")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payments {
    @Id
    @GeneratedValue
    private  int paymentId;
    private String orderId;
    private  String transId;
    private  String paymentStatus;
    private Date transDate;
    private  String transBy;

    public String getTransBy() {
        return transBy;
    }

    @Override
    public String toString() {
        return "Payments{" +
                "paymentId=" + paymentId +
                ", orderId='" + orderId + '\'' +
                ", transId='" + transId + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", transDate=" + transDate +
                ", transBy='" + transBy + '\'' +
                '}';
    }

    public void setTransBy(String transBy) {
        this.transBy = transBy;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTransId() {
        return transId;
    }

    public void setTransId(String transId) {
        this.transId = transId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate =  new Date();
    }
}
