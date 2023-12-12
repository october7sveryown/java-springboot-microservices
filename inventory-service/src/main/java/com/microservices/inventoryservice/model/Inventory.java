package com.microservices.inventoryservice.model;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class Inventory {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private String id;
    private String skuCode;
    private Integer qty;
}
