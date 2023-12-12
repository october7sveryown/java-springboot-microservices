package com.microservices.orderservice.dto;

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
