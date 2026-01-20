package com.ecommerce.online.Controller.order;
import com.ecommerce.online.Service.order.OrderService;
import com.ecommerce.online.dto.OrderDto;
import com.ecommerce.online.dto.OrderItemsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    @Autowired(required = false)
    private final OrderService orderService;

    @GetMapping("/viewItems/{orderId}")
    public List<OrderItemsDto> getOrderedItems(@PathVariable Long orderId)
    {
        return orderService.getOrderedItems(orderId);
    }

    @GetMapping("/viewOrder/{orderId}")
    public OrderDto getOrderDetails(@PathVariable Long orderId)
    {

        return orderService.getOrderDetails(orderId);
    }

    //Need to complete Vinnu
    @PostMapping("/placeOrder/{userId}")
    public void placeOrder(@PathVariable Long userId)
    {
        orderService.placeOrder(userId);
    }

}
