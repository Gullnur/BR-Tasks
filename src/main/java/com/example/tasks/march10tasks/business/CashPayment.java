package com.example.tasks.march10tasks.business;

import com.example.tasks.march10tasks.excep.InvalidPaymentException;

public class CashPayment implements PaymentStrategy {

    @Override
    public void validate(double amount) {
        if (amount <= 0) {
            throw new InvalidPaymentException("Amount must be greater than zero");
        }
    }

    @Override
    public void pay(double amount) {
        System.out.println(amount + " AZN paid with CASH");
    }
}
