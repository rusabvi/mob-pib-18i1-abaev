package com.example.demowebapp.domain;

public class Ware {
    final private int wareId;
    final private String wareName;
    final private double price;
    private int amount;

    public Ware(int wareId, String wareName, double price, int amount) {
        this.wareId = wareId;
        this.wareName = wareName;
        this.price = price;
        this.amount = amount;
    }

    public int getWareId() { return wareId; }

    public String getWareName() { return wareName; }

    public double getPrice() { return price; }

    public double getPrice(Person person) { return Shop.getPriceCoefficient(person) * price; }

    public int getAmount() { return amount; }

    public void subtractAmount(int amount) { this.amount -= amount; }

    public void increaseAmount(int amount) { this.amount += amount; }
}