package com.Bk24Shop.Shop.dto;

import com.Bk24Shop.Shop.enums.ECargoType;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class CargoDTO {

    private String name;

    @Enumerated(EnumType.STRING)
    private ECargoType type; //Truck, Van, Bike, Other

    private String address;

    private double latitude;
    private double longitude;

    // Getters and setters
}