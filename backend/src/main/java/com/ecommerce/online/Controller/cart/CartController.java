package com.ecommerce.online.Controller.cart;

import com.ecommerce.online.Service.cart.CartService;
import com.ecommerce.online.dto.CartItemsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private  final CartService cartService;

    @GetMapping("/items/{userId}")
    public List<CartItemsDto> getCartDetails(@PathVariable Long userId)
    {
        return cartService.getCartDetails(userId);
    }

    @PostMapping("/addItems")
    public void addItemsToCart(@RequestBody CartItemsDto cartItemsDto)
    {
        cartService.addItemsToCart(cartItemsDto);
    }

    @DeleteMapping("/deleteCartFull/{cartId}")
    public void deleteCartFull(@PathVariable Long cartId)
    {
        cartService.deleteCartFull(cartId);
    }




}
