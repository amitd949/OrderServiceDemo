package com.example.demo.paymentservice.beans;

import java.util.Date;

public class PaymentResponseDTO {
    private String orderId;
    private  String transId;
    private  String paymentStatus;
    private Date transDate;

    public PaymentResponseDTO(String orderId, String transId, String paymentStatus, Date transDate) {
        this.orderId = orderId;
        this.transId = transId;
        this.paymentStatus = paymentStatus;
        this.transDate = transDate;
    }

    @Override
    public String toString() {
        return "PaymentResponseDTO{" +
                "orderId='" + orderId + '\'' +
                ", transId='" + transId + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", transDate=" + transDate +
                '}';
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
        this.transDate = transDate;
    }
}
