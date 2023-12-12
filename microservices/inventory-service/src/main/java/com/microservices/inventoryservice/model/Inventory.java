package com.microservices.inventoryservice.model;


import jakarta.persistence.*;
import lombok.*;



@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "inventory_data")
public class Inventory {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String skuCode;
    private Integer qty;
}
