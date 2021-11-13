package com.example.demowebapp.domain;

import java.text.SimpleDateFormat;

public class Shop {
    private double priceCoefficient;

    public static double getPriceCoefficient(Person person) {
        if (new SimpleDateFormat("E").toString().equalsIgnoreCase("Воскресенье") && person.getRegular())
            return 0.8;
        else if (!new SimpleDateFormat("E").toString().equalsIgnoreCase("Воскресенье") && person.getRegular())
            return 0.85;
        else if (new SimpleDateFormat("E").toString().equalsIgnoreCase("Воскресенье") && !person.getRegular())
            return 0.9;
        return 1;
    }

    public static double getPriceCoefficient() {
        if (new SimpleDateFormat("E").toString().equalsIgnoreCase("Воскресенье"))
            return 0.9;
        return 1;
    }
}
