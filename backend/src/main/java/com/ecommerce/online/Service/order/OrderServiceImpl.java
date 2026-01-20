package com.ecommerce.online.Service.order;

import com.ecommerce.online.Entity.Cart;
import com.ecommerce.online.Entity.CartItems;
import com.ecommerce.online.Entity.Order;
import com.ecommerce.online.Entity.OrderItems;
import com.ecommerce.online.Repository.*;
import com.ecommerce.online.dto.OrderDto;
import com.ecommerce.online.dto.OrderItemsDto;
import com.ecommerce.online.exception.OrderItemsNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;


//@ConditionalOnProperty(name = "order.feature.enabled", havingValue = "false")
@Service
@RequiredArgsConstructor

public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final OrderItemsRepository orderItemsRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartItemsRepository cartItemsRepository;

    private final ModelMapper modelMapper;
    public List<OrderItemsDto> getOrderedItems(Long orderId)
    {
        List<OrderItems> orderItems = orderItemsRepository.findAllByOrderId(orderId);
        //exception
        if (orderItems == null || orderItems.isEmpty()) {
            throw new OrderItemsNotFoundException(
                    "No items found for orderId: " + orderId
            );
        }
        List<OrderItemsDto> orderItemsDtos = orderItems.stream()
                .map(orderItem -> new OrderItemsDto(
                        orderItem.getId(),
                        orderItem.getOrderId(),
                        orderItem.getProductId(),
                        orderItem.getQuantity(),
                        orderItem.getPrice())).toList();
        return orderItemsDtos;
    }

    public OrderDto getOrderDetails(Long orderId)
    {
        Order order = orderRepository.findByOrderId(orderId);
        //OrderDto orderDto = modelMapper.map(order, OrderDto.class);
        return mapToDto(order);
    }

    public OrderDto mapToDto(Order order)
    {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getOrderId());
        orderDto.setUserId(order.getUserId());
        orderDto.setCartId(order.getCartId());
        orderDto.setTotalAmount(order.getTotolAmount());
        orderDto.setOrderStatus(order.getOrderStatus());
        orderDto.setPaymentMethod(order.getPaymentMethod());
        orderDto.setCreatedAt(order.getCreatedAt());

        return orderDto;
    }

    public void placeOrder(Long userId) {
        Cart cart = cartRepository.findByCartId(userId);
        Order order = new Order();
        BigDecimal totalPrice = cartItemsRepository.sumOfAllPrice(cart.getCartId());
        order.setCartId(Math.toIntExact(cart.getCartId()));
        order.setUserId(userId);
        order.setTotolAmount(totalPrice);
        Order placedOrder=orderRepository.save(order);

        List<CartItems> cartItems = cartItemsRepository.findAllByCartId(cart.getCartId());
        List<OrderItems> orderItems = cartItems.stream()
                .map(cartItem -> new OrderItems(
                        placedOrder.getOrderId(),
                        cartItem.getProductId(),
                        cartItem.getQuantity(),
                        cartItem.getPrice()
                ))
                .toList();
        orderItemsRepository.saveAll(orderItems);

        //to delete cart id in cart table
        cartRepository.deleteByCartId(cart.getCartId());

    }
}
