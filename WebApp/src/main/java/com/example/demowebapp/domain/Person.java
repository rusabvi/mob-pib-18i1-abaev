package com.example.demowebapp.domain;

import java.util.*;

public class Person {

    private int id;
    private String name;
    private int pay;
    private final List<Payment> payments = new ArrayList<>();

    public Person(int id, String name, int pay) {
        this.id = id;
        this.name = name;
        this.pay = pay;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getPay() { return pay; }

    public void setPay(int pay) { this.pay = pay; }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public void addPayment(Payment payment) { payments.add(payment); }

    public List<Payment> getAllPayments() { return payments; }

}