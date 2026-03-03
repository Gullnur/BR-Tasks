package com.example.tasks.feb27tasks.task1;

public class Account {
    private double balance;
    private final String pin;

    public Account(double initialBalance, String pin) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("ilkin balans menfi ola bilmez");
        }
        if (pin == null || pin.length() != 4) {
            throw new IllegalArgumentException("pin deqiq 4 reqemden ibaret olmalidir2w");
        }
        this.balance = initialBalance;
        this.pin = pin;
    }

    public boolean checkPin(String inputPin) {
        return pin.equals(inputPin);
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("depozit meblegi > 0 olmalidir");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("cixarma meblegi > 0 olmalidir");
        }
        if (amount > balance) {
            throw new IllegalArgumentException("yetersiz bakiye  ");
        }
        balance -= amount;
    }
}