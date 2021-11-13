package com.example.demowebapp.domain;

import java.util.ArrayList;
import java.util.List;

public class Person {
    final private int id;
    final private String password;
    final private String name;
    private double money;
    private int shoppingAmount;
    private boolean regular;
    private List<Ware> personWares = new ArrayList<>();

    public Person(int id, String password, String name, double money) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.money = money;
        this.shoppingAmount = 0;
        this.regular = false;
    }

    public int getId() { return id; }

    public String getPassword() { return password; }

    public String getName() { return name; }

    public double getMoney() { return money; }

    public boolean getRegular() { return regular; }

    public void subtractMoney(double money) {
        this.money -= money;
        shoppingAmount ++;
        if (shoppingAmount > 4)
            regular = true;
    }

    public void addWare(Ware ware) {
        boolean isWare = false;
        for (Ware w : personWares)
            if (w.getWareName().equals(ware.getWareName())) {
                isWare = true;
                w.increaseAmount(ware.getAmount());
                break;
            }
        if (!isWare)
            personWares.add(ware);
    }

    public List<Ware> getAllPersonWares() { return personWares; }
}