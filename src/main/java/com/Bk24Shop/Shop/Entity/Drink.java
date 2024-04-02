package com.Bk24Shop.Shop.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Drink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;

}