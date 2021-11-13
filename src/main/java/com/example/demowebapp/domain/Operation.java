package com.example.demowebapp.domain;

import java.time.LocalDateTime;

public class Operation {
    final private int operationId;
    final private String wareName;
    final private int amount;
    final private double fullMoney;
    final private String buyerName;
    final private String fullDateTime;

    public Operation(int operationId,
                     Ware ware,
                     int amount,
                     Person person
    ) {
        this.operationId = operationId;
        this.wareName = ware.getWareName();
        this.amount = amount;
        this.fullMoney = ware.getPrice()
                * amount
                * Shop.getPriceCoefficient(person);
        this.buyerName = person.getName();
        this.fullDateTime = LocalDateTime.now().toString();
    }

    public int getOperationId() { return operationId; }

    public String getWareName() { return wareName; }

    public int getAmount() { return amount; }

    public double getFullMoney() { return fullMoney; }

    public String getBuyerName() { return buyerName; }

    public String getFullDateTime() { return fullDateTime; }
}