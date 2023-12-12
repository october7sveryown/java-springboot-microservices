package com.microservices.inventoryservice.service;


import com.microservices.inventoryservice.dao.InventoryRequest;
import com.microservices.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository repository;

    public boolean ifSkuCodeExists(InventoryRequest request){
        String skuCode=request.getSkuCode();
        return true;
    }
}
