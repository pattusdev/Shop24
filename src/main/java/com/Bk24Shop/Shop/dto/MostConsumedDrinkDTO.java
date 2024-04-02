package com.Bk24Shop.Shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MostConsumedDrinkDTO {

    private Long drinkId;
    private String drinkName;
    private Long totalQuantity;

}
