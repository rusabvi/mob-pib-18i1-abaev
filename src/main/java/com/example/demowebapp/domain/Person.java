package com.example.demowebapp.domain;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private int id;
    private String name;
    private List<Payment> payments = new ArrayList<>();

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
        addPayment(new Payment(7, 10_000, 9, 2021)); // начальное начисление
    }

    // геттеры
    public int getId() { return id; }

    public String getName() { return name; }

    public double getMediumPay() {
        double mediumPay = 0;
        for (Payment p : payments)
            mediumPay += p.getPayAfterNDFL();
        if (payments.size() > 0)
            mediumPay /= payments.size();
        return mediumPay;
    }

    public double getAllNDFL() {
        double allNDFL = 0;
        for (Payment p : payments)
            allNDFL += p.getNDFL();
        return allNDFL;
    }

    // сеттеры
    public void setId(int id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    //действия с платежами
    public List<Payment> getAllPayments() { return payments; }

    public Payment getPaymentByPayId(int payId) {
        for (Payment p : payments)
            if (p.getPayId() == payId)
                return p;
        return null;
    }

    public void addPayment(Payment payment) { payments.add(payment); }

    public void editPayment(Payment payment, int payBeforeNDFL, int month, int year) {
        payment.setPayBeforeNDFL(payBeforeNDFL);
        payment.setMonth(month);
        payment.setYear(year);
    }

    public void deletePayment(Payment payment) { payments.remove(payment); }
}