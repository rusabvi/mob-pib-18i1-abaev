package com.example.Shop.domain;

import com.example.Shop.entities.Operation;
import java.util.List;

public class Statistics {
    private List<Operation> operations;

    public Statistics(List<Operation> operations) { this.operations = operations; }

    public double getPaidMoney() {
        if (operations.size() == 0)
            return 0;
        return operations.stream()
                .mapToDouble((Operation o) -> o.getPrice())
                .sum();
    }

    public int getOperationAmount() {
        if (operations.size() == 0)
            return 0;
        return (int) operations.stream()
                .count();
    }

    public int getBuyerAmount() {
        if (operations.size() == 0)
            return 0;
        return (int) operations.stream()
                .map((Operation o) -> o.getBuyer().getName())
                .distinct()
                .count();
    }

    public double getAverageSum() {
        if (getOperationAmount() == 0)
            return 0;
        return (double)((int)(getPaidMoney() / getOperationAmount() * 100)) / 100;
    }

    public double getAverageBuying() {
        if (getBuyerAmount() == 0)
            return 0;
        return (double)((int)(getOperationAmount() / getBuyerAmount() * 100)) / 100;
    }

    public double getAverageSumSpentByOneBuyer() {
        if (getBuyerAmount() == 0)
            return 0;
        return (double)((int)(getPaidMoney() / getBuyerAmount() * 100)) / 100;
    }
}