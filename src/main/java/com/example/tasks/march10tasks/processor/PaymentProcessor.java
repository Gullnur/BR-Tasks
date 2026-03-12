package com.example.tasks.march10tasks.processor;

import com.example.tasks.march10tasks.business.CardPayment;
import com.example.tasks.march10tasks.business.CashPayment;
import com.example.tasks.march10tasks.business.PaymentStrategy;
import com.example.tasks.march10tasks.business.TransferPayment;
import com.example.tasks.march10tasks.model.PaymentRequest;
import com.example.tasks.march10tasks.model.PaymentType;

public class PaymentProcessor {
    public void process(PaymentRequest request) {

        PaymentStrategy strategy = getStrategy(request.getPaymentType());

        strategy.validate(request.getAmount());
        strategy.pay(request.getAmount());
    }

    private PaymentStrategy getStrategy(PaymentType type) {

        return switch (type) {
            case CASH -> new CashPayment();
            case CARD -> new CardPayment();
            case TRANSFER -> new TransferPayment();
        };
    }
}