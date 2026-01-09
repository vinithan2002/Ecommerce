package com.ecommerce.online.Service.cart;


import com.ecommerce.online.Entity.Cart;
import com.ecommerce.online.Entity.CartItems;
import com.ecommerce.online.Entity.Product;
import com.ecommerce.online.Repository.CartItemsRepository;
import com.ecommerce.online.Repository.CartRepository;
import com.ecommerce.online.Repository.ProductRepository;
import com.ecommerce.online.dto.CartDto;
import com.ecommerce.online.dto.CartItemsDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemsRepository cartItemsRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    public List<CartItemsDto> getCartDetails(Long userId) {

        Cart cart = cartRepository.findByUserId(userId);
        Long cartId = cart.getCartId();
        List<CartItems> cartItems = cartItemsRepository.findAllByCartId(cartId);
        List<CartItemsDto> cartItemsDtos = cartItems.stream()
                .map(item -> new CartItemsDto(
                        item.getCartItemId(),
                        item.getCartId(),
                        item.getProductId(),
                        item.getQuantity(),
                        item.getPrice()))
                .toList();

       return cartItemsDtos;
    }

    public void addItemsToCart(CartItemsDto cartItemsDto)
    {
        CartItems cartItems = modelMapper.map(cartItemsDto, CartItems.class);
        Long productId = cartItems.getProductId();
        Integer quantity = cartItems.getQuantity();
        Long cartId = cartItems.getCartId();
        Boolean productCartExist = cartItemsRepository.existsByProductIdAndCartId(productId,cartId);
        if(productCartExist == false){
            Product product = productRepository.findAllByProductId(productId);
            BigDecimal price = product.getPrice();
            System.out.println(price);
            if(cartItems.getPrice() == null)
                cartItems.setPrice(price);
            cartItemsRepository.save(cartItems);
        }
        else{
            int rq =cartItemsRepository.increaseQuantity(quantity,cartId,productId);
            System.out.println(rq);
        }
    }

    public void deleteCartFull(Long cartId) {
        //CartItems cart = cartItemsRepository.findById(id).get();
        cartItemsRepository.deleteAllByCartId(cartId);
    }

}