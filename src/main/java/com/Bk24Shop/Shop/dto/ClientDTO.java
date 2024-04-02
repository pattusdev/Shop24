package com.Bk24Shop.Shop.dto;

import com.example.BkShop24.Enums.ECargoType;
import com.example.BkShop24.model.Location;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientDTO {

    private String name;

    @Enumerated(EnumType.STRING)
    private ECargoType type; //Truck, Van, Bike, Other

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

}

// Getters and setters
