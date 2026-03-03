package com.example.tasks.feb27tasks.task2;

public class DailyLimitTracker {
    private final double dailyLimitAzn;
    private double usedTodayAzn;

    public DailyLimitTracker(double dailyLimitAzn) {
        if (dailyLimitAzn <= 0) {
            throw new IllegalArgumentException("daily limit > 0 ");
        }
        this.dailyLimitAzn = dailyLimitAzn;
        this.usedTodayAzn = 0;
    }

    public double getDailyLimitAzn() {
        return dailyLimitAzn;
    }

    public double getUsedTodayAzn() {
        return usedTodayAzn;
    }

    public double getRemainingAzn() {
        return dailyLimitAzn - usedTodayAzn;
    }

    public boolean canExchange(double amountAzn) {
        return amountAzn > 0 && (usedTodayAzn + amountAzn) <= dailyLimitAzn;
    }

    public void recordExchange(double amountAzn) {
        if (!canExchange(amountAzn)) {
            throw new IllegalArgumentException("daily limit kechdi");
        }
        usedTodayAzn += amountAzn;
    }
}