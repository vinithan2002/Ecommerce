package com.ecommerce.online.Controller.order;


import com.ecommerce.online.Entity.OrderItems;
import com.ecommerce.online.Service.order.OrderService;
import com.ecommerce.online.dto.OrderDto;
import com.ecommerce.online.dto.OrderItemsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/viewItems/{orderId}")
    public List<OrderItemsDto> getOrderItems(@PathVariable Long orderId)
    {
        return orderService.getOrderedItems(orderId);
    }

    @GetMapping("/viewOrder/{orderId}")
    public OrderDto getOrderDetails(@PathVariable Long orderId)
    {
        return orderService.getOrderDetails(orderId);
    }

    @PostMapping("/placeOrder/{userId}")
    public void placeOrder(@PathVariable Long userId)
    {
        orderService.placeOrder(userId);
    }

}
