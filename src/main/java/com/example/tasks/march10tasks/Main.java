package com.example.tasks.march10tasks;

import com.example.tasks.march10tasks.model.PaymentRequest;
import com.example.tasks.march10tasks.model.PaymentType;
import com.example.tasks.march10tasks.processor.PaymentProcessor;

public class Main {

    public static void main(String[] args) {

        PaymentRequest request =
                new PaymentRequest(50, PaymentType.CARD);

        PaymentProcessor processor = new PaymentProcessor();
        processor.process(request);
    }
}