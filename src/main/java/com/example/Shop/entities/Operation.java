package com.example.Shop.entities;

import com.example.Shop.domain.Shop;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "operations")
public class Operation {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", nullable = false)
    private int id;

    @Getter
    @Setter
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "Ware", nullable = false)
    private Ware ware;

    @Getter
    @Setter
    @Column(name = "Amount", nullable = false)
    private int amount;

    @Getter
    @Setter
    @Column(name = "Price", nullable = false)
    private double price;

    @Getter
    @Setter
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "Buyer", nullable = false)
    private Person buyer;

    @Getter
    @Setter
    @Column(name = "Date", nullable = false)
    private String date;

    @Getter
    @Setter
    @Column(name = "Time", nullable = false)
    private String time;

    public Operation(int id,
                     Ware ware,
                     int amount,
                     Person buyer) {
        this.id = id;
        this.ware = ware;
        this.ware.setPrice(
                ware.getPrice() *
                    Shop.getPriceCoefficient(buyer)
        );
        this.amount = amount;
        this.price = ware.getPrice() * amount * Shop.getPriceCoefficient(buyer);
        this.buyer = buyer;
        this.date = LocalDate.now().toString();
        this.time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
                //LocalTime.now().toString();
    }

    public Operation() {}
}