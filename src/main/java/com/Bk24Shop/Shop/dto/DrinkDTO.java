package com.Bk24Shop.Shop.dto;

import com.Bk24Shop.Shop.entity.Cargo;
import com.Bk24Shop.Shop.enums.EDrinkType;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class DrinkDTO {
    private String name;

    @Enumerated(EnumType.STRING)
    private EDrinkType type; //Milk,Juice,Soda,Water,Other

    private int quantity;

    @ManyToOne
    private Cargo cargo;
}



