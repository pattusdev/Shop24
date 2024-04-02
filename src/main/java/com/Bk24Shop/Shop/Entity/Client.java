package com.Bk24Shop.Shop.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private double latitude;
    private double longitude;

}
