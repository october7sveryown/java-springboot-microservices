package com.microservices.orderservice.service;

import com.microservices.orderservice.dto.OrderItemsDto;
import com.microservices.orderservice.dto.OrderRequest;
import com.microservices.orderservice.model.Order;
import com.microservices.orderservice.model.OrderItems;
import com.microservices.orderservice.repository.OrderRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setItems(orderRequest.getOrderItems().stream().map(
                this::mapToItems
        ).toList());
        orderRepository.save(order);
    }

    private OrderItems mapToItems(OrderItemsDto orderItemsDto) {
        OrderItems orderItem = new OrderItems();
        orderItem.setSkuCode(orderItemsDto.getSkuCode());
        orderItem.setPrice(orderItemsDto.getPrice());
        orderItem.setQty(orderItemsDto.getQty());
        return orderItem;
    }
}
