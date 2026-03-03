package com.example.tasks.feb27tasks.task1;

import org.springframework.scheduling.config.Task;

import java.util.Scanner;

public class Main1 extends Object {
    public static void main(String[] args) {
        // demo --- balance = 100, pin = 1234
        Account account = new Account(100.0, "1234");

        try (Scanner scanner = new Scanner(System.in)) {
            ATM atm = new ATM(account, scanner);
            atm.start();
        }
    }
}
