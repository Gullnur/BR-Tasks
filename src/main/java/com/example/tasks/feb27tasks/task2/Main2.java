package com.example.tasks.feb27tasks.task2;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        /**
         1 AZN = 0.588 USD
         Commission = 1.5%
         Daily limit = 2000 AZN **/

        DailyLimitTracker tracker = new DailyLimitTracker(2000.0);

        try (Scanner scanner = new Scanner(System.in)) {
            CurrencyExchangeApp app = new CurrencyExchangeApp(scanner, tracker, 0.588, 1.5);
            app.start();
        }
    }
}