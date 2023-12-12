package com.microservices.inventoryservice.controller;

import com.microservices.inventoryservice.dao.InventoryRequest;
import com.microservices.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    public boolean ifSkuExists(@PathVariable("sku-code") String skuCode){
        return inventoryService.ifSkuCodeExists(skuCode);

    }

}
