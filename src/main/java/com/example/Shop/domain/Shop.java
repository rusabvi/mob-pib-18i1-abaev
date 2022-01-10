package com.example.Shop.domain;

import com.example.Shop.entities.Operation;
import com.example.Shop.entities.Person;
import com.example.Shop.entities.Product;
import com.example.Shop.repositories.OperationRepository;
import java.time.LocalDate;

public class Shop {
    public static double getPriceCoefficient(Person buyer) {
        if (buyer.isRegular())
            return getPriceCoefficient() - 0.15;
        return getPriceCoefficient();
    }

    public static double getPriceCoefficient() {
        if (LocalDate.now().getDayOfWeek().toString().equals("SUNDAY"))
            return 0.9;
        return 1;
    }

    public static boolean canBuyerBuyProduct(
            Person buyer,
            Product product,
            int amount
    ) {
        if (amount == 0
                || buyer.getMoney()
                < product.getWare().getPrice()
                * amount
                * Shop.getPriceCoefficient(buyer))
            return false;
        return true;
    }

    public static void makeDeal(
            Person buyer,
            Product product,
            int amount,
            OperationRepository operationRepository
    ) {
        buyer.subtractMoney(
                product.getWare().getPrice()
                        * amount
                        * getPriceCoefficient(buyer)
        );
        product.subtractAmount(amount);
        operationRepository.save(new Operation(
                0,
                product.getWare(),
                amount,
                buyer
        ));
    }
}