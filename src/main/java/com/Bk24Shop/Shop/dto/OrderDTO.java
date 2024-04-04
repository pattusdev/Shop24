package com.Bk24Shop.Shop.dto;

import com.Bk24Shop.Shop.entity.Client;
import com.Bk24Shop.Shop.enums.EOrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDTO {



    private Client client;


    private EOrderStatus status; //Pending,Completed,Cancelled


    private LocalDateTime timestamp;

    // Getters and setters
}
