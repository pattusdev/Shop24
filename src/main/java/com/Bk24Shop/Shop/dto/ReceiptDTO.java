package com.Bk24Shop.Shop.dto;

import com.Bk24Shop.Shop.entity.Order;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReceiptDTO {

    @OneToOne
    private Order order;

    private LocalDateTime timestamp;

    // Getters and setters
}