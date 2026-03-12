package com.example.tasks.march10tasks.model;

public class PaymentRequest {
    private double amount;
    private PaymentType paymentType;

    public PaymentRequest(double amount, PaymentType paymentType) {
        this.amount = amount;
        this.paymentType = paymentType;

    }

   public double getAmount() {
        return amount;

    }
    public PaymentType getPaymentType() {
    return paymentType;}
}
