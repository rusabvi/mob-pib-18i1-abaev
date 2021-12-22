package com.example.Shop.entities;

import com.example.Shop.domain.Shop;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Wares")
public class Ware {
    @Getter
    @Setter
    @Id
    @Column(name = "Id", nullable = false)
    private int id;

    @Getter
    @Setter
    @Column(name = "Name", length = 15, nullable = false)
    private String name;

    @Setter
    @Column(name = "Price", nullable = false)
    private double price;

    @Getter
    @Setter
    @OneToOne(mappedBy = "ware")
    private Product product;

    @Getter
    @Setter
    @OneToMany(mappedBy = "ware", fetch = FetchType.EAGER)
    private List<Operation> operations;

    public Ware(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Ware() {}

    public double getPrice() { return price * Shop.getPriceCoefficient(); }
}