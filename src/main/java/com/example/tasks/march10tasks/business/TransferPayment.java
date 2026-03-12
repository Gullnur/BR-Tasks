package com.example.tasks.march10tasks.business;

import com.example.tasks.march10tasks.excep.InvalidPaymentException;

public class TransferPayment implements PaymentStrategy {

    @Override
    public void validate(double amount) {
        if (amount < 10) {
            throw new InvalidPaymentException("Minimum transfer is 10 AZN");
        }
    }

    @Override
    public void pay(double amount) {
        System.out.println(amount + " AZN paid via BANK TRANSFER");
    }
}