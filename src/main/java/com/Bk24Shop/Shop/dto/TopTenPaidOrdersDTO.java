package com.Bk24Shop.Shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class TopTenPaidOrdersDTO {
    private Long orderId;
    private String ClientNames;
}
