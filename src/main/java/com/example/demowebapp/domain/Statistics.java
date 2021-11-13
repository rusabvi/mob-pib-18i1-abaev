package com.example.demowebapp.domain;

import java.util.List;

public class Statistics {
    private double paidMoney;
    private int operationAmount;
    private int buyerAmount;

    public Statistics(List<Operation> operations) {
        if (operations.size() == 0) {
            paidMoney = 0;
            operationAmount = 0;
            buyerAmount = 0;
        } else {
            paidMoney = operations.stream()
                    .mapToDouble((Operation o) -> o.getFullMoney())
                    .sum();
            operationAmount = (int) operations.stream()
                    .count();
            buyerAmount = (int) operations.stream()
                    .map((Operation o) -> o.getBuyerName())
                    .distinct()
                    .count();
        }
    }

    public double getPaidMoney() { return paidMoney; }

    public int getOperationAmount() { return operationAmount; }

    public int getBuyerAmount() { return buyerAmount; }

    public double getAverageSum() {
        if (operationAmount == 0)
            return 0;
        return (double)((int)(paidMoney / operationAmount * 100)) / 100;
    }

    public double getAverageBuying() {
        if (buyerAmount == 0)
            return 0;
        return (double)((int)(operationAmount / buyerAmount * 100)) / 100;
    }

    public double getAverageSumSpentByOneBuyer() {
        if (buyerAmount == 0)
            return 0;
        return (double)((int)(paidMoney / buyerAmount * 100)) / 100;
    }
}