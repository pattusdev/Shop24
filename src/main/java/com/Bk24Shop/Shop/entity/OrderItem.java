package com.Bk24Shop.Shop.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class OrderItem {
    @EmbeddedId
    private OrderItemId id;

    @ManyToOne
    @MapsId("orderId")
    private Order order;

    @ManyToOne
    @MapsId("drinkId")
    private Drink drink;

    private int quantity;

    // Getters and setters
}

