package com.example.Shop.domain;

import com.example.Shop.entities.Person;
import java.text.SimpleDateFormat;

public class Shop {
    public static double getPriceCoefficient(Person person) {
        if (new SimpleDateFormat("E").toString().equalsIgnoreCase("Воскресенье") && person.isRegular())
            return 0.8;
        else if (!new SimpleDateFormat("E").toString().equalsIgnoreCase("Воскресенье") && person.isRegular())
            return 0.85;
        else if (new SimpleDateFormat("E").toString().equalsIgnoreCase("Воскресенье") && !person.isRegular())
            return 0.9;
        return 1;
    }

    public static double getPriceCoefficient() {
        if (new SimpleDateFormat("E").toString().equalsIgnoreCase("Воскресенье"))
            return 0.9;
        return 1;
    }
}