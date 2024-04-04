package com.Bk24Shop.Shop.dto;

import com.Bk24Shop.Shop.entity.Drink;
import com.Bk24Shop.Shop.entity.Order;
import com.Bk24Shop.Shop.entity.OrderItemId;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.Data;

@Data
public class OrderItemDTO {

    @ManyToOne
    @MapsId("orderId")
    private Order order;

    @ManyToOne
    @MapsId("drinkId")
    private Drink drink;

    private int quantity;

    // Getters and setters
}