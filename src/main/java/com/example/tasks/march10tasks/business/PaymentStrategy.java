package com.example.tasks.march10tasks.business;

public interface PaymentStrategy {
    void validate(double amount);
    void pay(double amount);
}
