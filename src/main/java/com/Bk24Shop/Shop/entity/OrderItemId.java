package com.Bk24Shop.Shop.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class OrderItemId implements Serializable {

    private Long orderId;
    private Long drinkId;

    public OrderItemId() {
    }

    public OrderItemId(Long orderId, Long drinkId) {
        this.orderId = orderId;
        this.drinkId = drinkId;
    }

    // Equals and hashCode methods
}

