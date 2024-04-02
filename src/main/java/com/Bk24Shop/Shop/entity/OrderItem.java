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
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @MapsId("drinkId")
    @JoinColumn(name = "drink_id")
    private Drink drink;

    private int quantity;

    // Getters and setters
}

