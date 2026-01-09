package com.ecommerce.online.Service.order;

import com.ecommerce.online.dto.OrderDto;
import com.ecommerce.online.dto.OrderItemsDto;

import java.util.List;

public interface OrderService {
    List<OrderItemsDto> getOrderedItems(Long orderId);
    OrderDto getOrderDetails(Long orderId);
    void placeOrder(Long userId);
}
