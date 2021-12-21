package com.example.Shop.entities;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Persons")
public class Person {
    @Getter
    @Setter
    @Id
    @Column(name = "Id", unique = true, nullable = false)
    private int id;

    @Getter
    @Setter
    @Column(name = "Password", length = 15, nullable = false)
    private String password;

    @Getter
    @Setter
    @Column(name = "Name", length = 15, nullable = false)
    private String name;

    @Getter
    @Setter
    @Column(name = "Money", nullable = false)
    private double money;

    @Getter
    @Setter
    @Column(name = "Visits")
    private int visits = 0;

    @Getter
    @Setter
    @Column(name = "Regular")
    private boolean regular = false;

    @Getter
    @Setter
    @OneToMany(mappedBy = "buyer", fetch = FetchType.EAGER)
    private List<Operation> operations;

    public Person(
            int id,
            String password,
            String name,
            double money
    ) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.money = money;
        this.visits = 0;
        this.regular = false;
    }

    public Person() {}

    public void subtractMoney(double money) { this.money -= money; }
}