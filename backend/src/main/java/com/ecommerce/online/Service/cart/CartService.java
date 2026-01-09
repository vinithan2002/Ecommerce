package com.ecommerce.online.Service.cart;

import com.ecommerce.online.dto.CartItemsDto;

import java.util.List;

public interface CartService {
    public List<CartItemsDto> getCartDetails(Long userId);
    void addItemsToCart(CartItemsDto cartItemsDto);
    public void deleteCartFull(Long cartId);
}
