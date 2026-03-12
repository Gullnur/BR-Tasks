package com.example.tasks.march10tasks.business;

import com.example.tasks.march10tasks.excep.InvalidPaymentException;

    public class CardPayment implements PaymentStrategy {

        @Override
        public void validate(double amount) {
            if (amount < 1) {
                throw new InvalidPaymentException("Minimum card payment is 1 AZN");
            }
        }

        @Override
        public void pay(double amount) {
            System.out.println(amount + " AZN paid with CARD");
        }
    }