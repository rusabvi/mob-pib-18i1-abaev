package com.example.demowebapp.domain;

public class Payment {
    private int payId;
    private int pay;
    private String month;
    private String year;

    public Payment(int payId, int pay, String month, String year) {
        this.payId = payId;
        this.pay = pay;
        this.month = month;
        this.year = year;
    }

    // геттеры
    public int getPayId() { return payId;}

    public int getPay() { return pay; }

    public String getMonth() { return month; }

    public String getYear() { return year; }

    // сеттеры
    public void setPayId(int payId) { this.payId = payId; }

    public void setPay(int pay) { this.pay = pay; }

    public void setMonth(String month) { this.month = month; }

    public void setYear(String year) { this.year = year; }
}