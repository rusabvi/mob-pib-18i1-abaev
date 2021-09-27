package com.example.demowebapp.domain;

public class Payment {
    private int pay;
    private String month;
    private String year;

    public Payment(int pay, String month, String year) {
        this.pay = pay;
        this.month = month;
        this.year = year;
    }

    public int getPay() { return pay; }

    public void setPay(int pay) { this.pay = pay; }

    public String getMonth() { return month; }

    public void setMonth(String month) { this.month = month; }

    public String getYear() { return year; }

    public void setYear(String year) { this.year = year; }
}