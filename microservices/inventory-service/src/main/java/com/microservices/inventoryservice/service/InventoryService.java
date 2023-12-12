package com.microservices.inventoryservice.service;


import com.microservices.inventoryservice.dao.InventoryRequest;
import com.microservices.inventoryservice.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryService {

    private final InventoryRepository repository;

    public boolean ifSkuCodeExists(String skuCode){
        long count = repository.findBySkuCode(skuCode).size();
        return count > 0;
    }
}
