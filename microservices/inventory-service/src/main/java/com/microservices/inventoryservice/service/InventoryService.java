package com.microservices.inventoryservice.service;


import com.microservices.inventoryservice.dao.InventoryRequest;
import com.microservices.inventoryservice.dao.InventoryResponse;
import com.microservices.inventoryservice.model.Inventory;
import com.microservices.inventoryservice.repository.InventoryRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class InventoryService {

    private final InventoryRepository repository;

    public List<InventoryResponse> ifSkuCodeExists(List<String> skuCodes){
        log.info("checking inventory");
        return repository.findBySkuCodeIn(skuCodes).stream().map(
                this::mapResponse).toList();
    }

    private InventoryResponse mapResponse(Inventory inventory) {
        return InventoryResponse.builder().skuCode(inventory.getSkuCode()!=null ? inventory.getSkuCode() : "unknown item").isAvailable(inventory.getQty()>0 && inventory.getSkuCode()!=null).build();
    }
}
