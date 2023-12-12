package com.microservices.inventoryservice.controller;

import com.microservices.inventoryservice.dao.InventoryRequest;
import com.microservices.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping
    public String ifSkuExists(@RequestBody InventoryRequest request){
        inventoryService.ifSkuCodeExists(request);
        return "Sku exisits";
    }

}
