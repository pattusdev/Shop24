package com.Bk24Shop.Shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopOrderDTO {
    private Long orderId;
    private Long clientId;
    private LocalDateTime orderDate;

    // Getters and setters
}
