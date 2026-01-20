package com.ecommerce.online.Repository;

import com.ecommerce.online.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart,Long> {

    Cart findByUserId(Long userId);

    Cart findByCartId(Long userId);

    void deleteByCartId(Long cartId);
}
