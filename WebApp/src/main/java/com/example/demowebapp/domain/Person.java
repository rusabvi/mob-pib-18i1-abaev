package com.example.demowebapp.domain;

import java.util.ArrayList;
import java.util.List;

public class Person {
    private int id;
    private String name;
    private int pay;
    private List<Payment> payments = new ArrayList<>();

    public Person(int id, String name, int pay) {
        this.id = id;
        this.name = name;
        this.pay = pay;
        addPayment(new Payment(7, 10_000, "September", "2021")); // начальное начисление
    }

    // геттеры
    public int getId() { return id; }

    public String getName() { return name; }

    public int getPay() { return pay; }

    // сеттеры
    public void setId(int id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setPay(int pay) { this.pay = pay; }

    // передаёт список всех начислений
    public List<Payment> getAllPayments() { return payments; } // передаёт список начислений сотрудника

    public Payment getPaymentByPayId(int payId) {
        for (Payment p : payments)
            if (p.getPayId() == payId)
                return p;
        return null;
    }

    public void editPayment(Payment payment, int pay, String month, String year) {
        payment.setPay(pay);
        payment.setMonth(month);
        payment.setYear(year);
    }

    // создаёт новое начисление
    public void addPayment(Payment payment) { payments.add(payment); }

    public void deletePayment(Payment payment) { payments.remove(payment); }
}