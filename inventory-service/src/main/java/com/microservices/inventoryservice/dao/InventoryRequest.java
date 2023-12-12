package com.microservices.inventoryservice.dao;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class InventoryRequest {

    private String skuCode;
}
