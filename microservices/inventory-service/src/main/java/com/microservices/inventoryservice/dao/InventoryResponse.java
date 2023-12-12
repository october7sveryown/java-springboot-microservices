package com.microservices.inventoryservice.dao;

import lombok.*;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InventoryResponse {
    private String skuCode;
    private boolean isAvailable;
}
