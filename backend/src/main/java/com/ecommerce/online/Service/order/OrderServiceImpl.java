package com.ecommerce.online.Service.order;

import com.ecommerce.online.Entity.Cart;
import com.ecommerce.online.Entity.CartItems;
import com.ecommerce.online.Entity.Order;
import com.ecommerce.online.Entity.OrderItems;
import com.ecommerce.online.Repository.*;
import com.ecommerce.online.dto.OrderDto;
import com.ecommerce.online.dto.OrderItemsDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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
        Long cartId = cart.getCartId();
        Order order = new Order();
        BigDecimal totalPrice = cartItemsRepository.sumOfAllPrice(cartId);
        order.setCartId(Math.toIntExact(cartId));
        order.setUserId(userId);
        order.setTotolAmount(totalPrice);

        orderRepository.save(order);

        Order order1 = orderRepository.findByCartId(cartId);
        Long orderId = order1.getOrderId();

        List<CartItems> cartItems = cartItemsRepository.findAllByCartId(cartId);

        List<OrderItems> orderItems = cartItems.stream()
                .map(cartItem -> new OrderItems(
                        orderId,
                        cartItem.getProductId(),
                        cartItem.getQuantity(),
                        cartItem.getPrice()
                ))
                .toList();

        orderItemsRepository.saveAll(orderItems);

    }
}
