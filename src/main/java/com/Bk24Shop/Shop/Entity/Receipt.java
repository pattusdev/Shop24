package com.Bk24Shop.Shop.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long receiptId;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private LocalDateTime timestamp;

    // Getters and setters
}

