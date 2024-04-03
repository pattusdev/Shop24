package com.Bk24Shop.Shop.entity;

import com.Bk24Shop.Shop.enums.ECargoType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ECargoType type; //Truck, Van, Bike, Other

    private String address;

    private double latitude;
    private double longitude;

    // Getters and setters
}

