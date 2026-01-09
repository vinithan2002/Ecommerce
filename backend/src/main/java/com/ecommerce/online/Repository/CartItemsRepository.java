package com.ecommerce.online.Repository;

import com.ecommerce.online.Entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

public interface CartItemsRepository extends JpaRepository<CartItems,Long> {


    CartItems findByCartId(Long id);

    List<CartItems> findAllByCartId(Long cartId);

    @Modifying
    @Transactional
    @Query(
            """
                    DELETE FROM CartItems c 
                    WHERE c.cartId = :cartId"""
    )
    void deleteAllByCartId(Long cartId);

    Boolean existsByProductId(Long productID);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query(
            """
                    UPDATE CartItems c
                    SET c.quantity = c.quantity + :quantity
                    WHERE c.cartId = :cartId AND c.productId = :productId
            """
    )
    int increaseQuantity(Integer quantity,Long cartId, Long productId);

    Boolean existsByCartId(Long cartId);


    Boolean existsByProductIdAndCartId(Long productId, Long cartId);

    @Query(
            """
                   select sum(ci.price * ci.quantity)
                   from CartItems ci where ci.cartId = :cartId"""
    )
    BigDecimal sumOfAllPrice(long cartId);
}
