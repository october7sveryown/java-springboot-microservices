package com.microservices.inventoryservice;

import com.microservices.inventoryservice.model.Inventory;
import com.microservices.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository repository){
		return args -> {
			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("iphone15");
			inventory1.setQty(100);

			Inventory inventory2 = new Inventory();
			inventory2.setSkuCode("iphone 14 pro");
			inventory2.setQty(10);

			repository.save(inventory1);
			repository.save(inventory2);
		};
	}
}
