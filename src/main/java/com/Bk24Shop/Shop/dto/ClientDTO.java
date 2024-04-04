package com.Bk24Shop.Shop.dto;

import com.Bk24Shop.Shop.enums.ECargoType;
import com.Bk24Shop.Shop.enums.EClientType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    private String name;

    @Enumerated(EnumType.STRING)
    private EClientType type; //Truck, Van, Bike, Other

    private String address;

    private double latitude;
    private double longitude;
}

// Getters and setters
