package com.Bk24Shop.Shop.entity;

import com.Bk24Shop.Shop.enums.EOrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "`order`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Enumerated(EnumType.STRING)
    private EOrderStatus status; //Pending,Completed,Cancelled


    private LocalDateTime timestamp;

    // Getters and setters
}

