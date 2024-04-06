package com.Bk24Shop.Shop.entity;

import com.Bk24Shop.Shop.enums.EDrinkType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Drink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private EDrinkType type; //Milk,Juice,Soda,Water,Other

    private int quantity;

    @ManyToOne
    private Cargo cargo;

    // Getters and setters
}

