package com.microservices.orderservice.service;

import com.microservices.orderservice.config.WebClientConfig;
import com.microservices.orderservice.dto.InventoryResponse;
import com.microservices.orderservice.dto.OrderItemsDto;
import com.microservices.orderservice.dto.OrderRequest;
import com.microservices.orderservice.model.Order;
import com.microservices.orderservice.model.OrderItems;
import com.microservices.orderservice.repository.OrderRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;

    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setItems(orderRequest.getOrderItems().stream().map(
                this::mapToItems
        ).toList());

        List<String> skuCodes = order.getItems().stream().map(
                OrderItems::getSkuCode
        ).toList();

        //check if order items are in stock by calling inventory service
        InventoryResponse[] result = webClientBuilder.build().get().
                uri("http://localhost:8082/api/v1/inventory", uriBuilder -> uriBuilder.queryParam(
                        "skuCode",skuCodes).build())
                        .retrieve().bodyToMono(InventoryResponse[].class).block();

        //check if all products are in stock
        boolean allProductsInStock=false;
        if(result.length>0){
            allProductsInStock = Arrays.stream(result).allMatch(InventoryResponse::isAvailable);
        }


        if(allProductsInStock){
            orderRepository.save(order);
        } else{
            throw new IllegalArgumentException("Product not in stock");
        }


    }

    private OrderItems mapToItems(OrderItemsDto orderItemsDto) {
        OrderItems orderItem = new OrderItems();
        orderItem.setSkuCode(orderItemsDto.getSkuCode());
        orderItem.setPrice(orderItemsDto.getPrice());
        orderItem.setQty(orderItemsDto.getQty());
        return orderItem;
    }
}
