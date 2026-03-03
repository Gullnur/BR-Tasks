package com.example.tasks.feb27tasks.task2;

import java.util.Scanner;

public class CurrencyExchangeApp {
    private final Scanner scanner;
    private final DailyLimitTracker limitTracker;

    private double rateAznToUsd;        // 1 AZN neche USD
    private double commissionPercent;   // ex; 1.5%

    public CurrencyExchangeApp(Scanner scanner,
                               DailyLimitTracker limitTracker,
                               double rateAznToUsd,
                               double commissionPercent) {
        this.scanner = scanner;
        this.limitTracker = limitTracker;
        setRate(rateAznToUsd);
        setCommissionPercent(commissionPercent);
    }

    public void start() {
        System.out.println("===== exchange calculator (AZN -> USD) =====");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("sechim sechin: ");

            switch (choice) {
                case 1 -> handleExchange();
                case 2 -> showSettings();
                case 3 -> updateRateAndCommission();
                case 4 -> showDailyLimitStatus();
                case 5 -> {
                    System.out.println("bye-bye!!!!!");
                    running = false;
                }
                default -> System.out.println("invalid option!!!!! Try again!!!");
            }
        }
    }

    private void printMenu() {
        System.out.println("1) AZN -> USD");
        System.out.println("2) cari tarifi və komissiyani göstermek");
        System.out.println("3) update rate & commission");
        System.out.println("4) daily limit status");
        System.out.println("5) exit");
    }

    private void handleExchange() {
        double amountAzn = readDouble("deyishmek uchun AZN meblegini yazin: ");

        if (amountAzn <= 0) {
            System.out.println("mebleg > 0 olmalidir");
            return;
        }

        if (!limitTracker.canExchange(amountAzn)) {
            System.out.println("rejected !!!!! daily limit kechdi!!!");
            System.out.println("remaining today: " + formatMoney(limitTracker.getRemainingAzn()) + " AZN");
            return;
        }

        // umumi
        double grossUsd = amountAzn * rateAznToUsd;
        double commissionUsd = grossUsd * (commissionPercent / 100.0);
        double netUsd = grossUsd - commissionUsd;

               limitTracker.recordExchange(amountAzn);

        System.out.println("Okeiş Hesabat:");
        System.out.println("Rate: 1 AZN = " + rateAznToUsd + " USD");
        System.out.println("Gross USD: " + formatUsd(grossUsd));
        System.out.println("Commission (" + commissionPercent + "%): " + formatUsd(commissionUsd));
        System.out.println("Net USD: " + formatUsd(netUsd));
        System.out.println("Used today: " + formatMoney(limitTracker.getUsedTodayAzn()) + " AZN");
        System.out.println("Remaining: " + formatMoney(limitTracker.getRemainingAzn()) + " AZN");
    }

    private void showSettings() {
        System.out.println("Current rate: 1 AZN = " + rateAznToUsd + " USD");
        System.out.println("Commission: " + commissionPercent + "%");
    }

    private void updateRateAndCommission() {
        double newRate = readDouble("Enter new rate (1 AZN = ? USD): ");
        double newCommission = readDouble("Enter new commission percent: ");

        try {
            setRate(newRate);
            setCommissionPercent(newCommission);
            System.out.println("updated");
        } catch (IllegalArgumentException e) {
            System.out.println("update failed!!! " + e.getMessage());
        }
    }

    private void showDailyLimitStatus() {
        System.out.println("gunluk limit: " + formatMoney(limitTracker.getDailyLimitAzn()) + " AZN");
        System.out.println("bugun isledilen: " + formatMoney(limitTracker.getUsedTodayAzn()) + " AZN");
        System.out.println("qalan: " + formatMoney(limitTracker.getRemainingAzn()) + " AZN");
    }

    private void setRate(double rate) {
        if (rate <= 0) throw new IllegalArgumentException("Rate must be > 0.");
        this.rateAznToUsd = rate;
    }

    private void setCommissionPercent(double commissionPercent) {
        if (commissionPercent < 0) throw new IllegalArgumentException("komissiya menfi ola bilmez");
        this.commissionPercent = commissionPercent;
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("pls enter valid eded");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim().replace(",", ".");
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("pls enter valid eded");
            }
        }
    }

    private String formatMoney(double value) {
        return String.format("%.2f", value);
    }

    private String formatUsd(double usd) {
        return String.format("%.2f USD", usd);
    }
}