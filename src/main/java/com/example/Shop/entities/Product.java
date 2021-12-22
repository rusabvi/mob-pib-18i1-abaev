package com.example.Shop.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "Products")
public class Product {
    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id", nullable = false)
    private int id;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "Ware_id", nullable = false)
    private Ware ware;

    @Getter
    @Setter
    @Column(name = "Amount", nullable = false)
    private int amount;

    public Product(Ware ware, int amount) {
        this.ware = ware;
        this.amount = amount;
    }

    public Product() {}

    public void subtractAmount(int amount) { this.amount -= amount; }
}