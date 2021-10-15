package com.example.demowebapp.domain;

public class Payment {
    private static final int percentNDFL = 13;
    private int payId;
    private double payBeforeNDFL;
    private String month;
    private int year;

    public Payment(int payId, double payBeforeNDFL, int monthInt, int year) {
        this.payId = payId;
        this.payBeforeNDFL = payBeforeNDFL;
        this.month = convertMonthToString(monthInt);
        this.year = year;
    }

    // геттеры
    public int getPayId() { return payId; }

    public double getPayBeforeNDFL() { return payBeforeNDFL; }

    public double getPayAfterNDFL() { return payBeforeNDFL * (100 - percentNDFL) / 100; }

    public double getNDFL() { return payBeforeNDFL * percentNDFL / 100; }

    public String getMonth() { return month; }

    public int getMonthInt() {
        if (this.month == "January")
            return 1;
        else if (this.month == "February")
            return 2;
        else if (this.month == "March")
            return 3;
        else if (this.month == "April")
            return 4;
        else if (this.month == "May")
            return 5;
        else if (this.month == "June")
            return 6;
        else if (this.month == "July")
            return 7;
        else if (this.month == "August")
            return 8;
        else if (this.month == "September")
            return 9;
        else if (this.month == "October")
            return 10;
        else if (this.month == "November")
            return 11;
        else if (this.month == "December")
            return 12;
        else
            return -1;
    }

    public int getYear() { return year; }

    // сеттеры
    public void setPayId(int payId) { this.payId = payId; }

    public void setPayBeforeNDFL(int payBeforeNDFL) { this.payBeforeNDFL = payBeforeNDFL; }

    public void setMonth(int monthInt) { this.month = convertMonthToString(monthInt); }

    public void setYear(int year) { this.year = year; }

    //перевод месяца из цифры в слово
    private String convertMonthToString(int monthInt) {
        if (monthInt == 1)
            return "January";
        else if (monthInt == 2)
            return "February";
        else if (monthInt == 3)
            return "March";
        else if (monthInt == 4)
            return "April";
        else if (monthInt == 5)
            return "May";
        else if (monthInt == 6)
            return "June";
        else if (monthInt == 7)
            return "July";
        else if (monthInt == 8)
            return "August";
        else if (monthInt == 9)
            return "September";
        else if (monthInt == 10)
            return "October";
        else if (monthInt == 11)
            return "November";
        else if (monthInt == 12)
            return "December";
        else
            return null;
    }
}