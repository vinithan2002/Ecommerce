package com.ecommerce.online.Repository;

import com.ecommerce.online.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrderId(Long orderId);

    Order findByCartId(Long cartId);
}
