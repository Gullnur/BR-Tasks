package com.example.tasks.feb27tasks.task1;

import java.util.Scanner;

public class ATM {
    private final Account account;
    private final Scanner scanner;

    private static final int MAX_PIN_ATTEMPTS = 3;

    public ATM(Account account, Scanner scanner) {
        this.account = account;
        this.scanner = scanner;
    }

    public void start() {
        System.out.println("=== GG's ATM ===");

        if (!verifyPin()) {
            System.out.println("heddinden artiq yanlis cehdler!! kart bloklanib!!!");
            return;
        }

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("sechim sechin:");

            switch (choice) {
                case 1 -> handleDeposit();
                case 2 -> handleWithdraw();
                case 3 -> showBalance();
                case 4 -> {
                    System.out.println("bye-bye!! ");
                    running = false;
                }
                default -> System.out.println("yanlis sechim. try again!! ");
            }
        }
    }

    private boolean verifyPin() {
        for (int attempt = 1; attempt <= MAX_PIN_ATTEMPTS; attempt++) {
            System.out.print("Enter Ppin (4 reqemli):");
            String input = scanner.nextLine().trim();

            if (account.checkPin(input)) {
                System.out.println("pin is ok!!");
                return true;
            } else {
                int left = MAX_PIN_ATTEMPTS - attempt;
                System.out.println("SEHV PIN!!!. qalan cehd sayi:" + left);
            }
        }
        return false;
    }

    private void printMenu() {
        System.out.println();
        System.out.println("=== MENU ===");
        System.out.println("1) Deposit");
        System.out.println("2) Withdraw");
        System.out.println("3) Balance");
        System.out.println("4) Exit");
    }

    private void handleDeposit() {
        double amount = readDouble("depozit meblegini daxil edin: q");
        try {
            account.deposit(amount);
            System.out.println("depozit is ok!! yeni balans: " + formatMoney(account.getBalance()));
        } catch (IllegalArgumentException e) {
            System.out.println("Deposit failed: " + e.getMessage());
        }
    }

    private void handleWithdraw() {
        double amount = readDouble("chixarish meblegini daxil edin: ");
        try {
            account.withdraw(amount);
            System.out.println("ugurlu chixarish!! yeni balans " + formatMoney(account.getBalance()));
        } catch (IllegalArgumentException e) {
            System.out.println("Gchixarish ugursuz oldu: " + e.getMessage());
        }
    }

    private void showBalance() {
        System.out.println("hal-hazirdaki balance: " + formatMoney(account.getBalance()));
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println(" valid tam ədəd daxil edin.");
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
                System.out.println("please duzgun valid number!!!! ");
            }
        }
    }

    private String formatMoney(double value) {
        return String.format("%.2f AZN", value);
    }
}
