package com.ecommerce.online.Repository;


import com.ecommerce.online.Entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Integer> {
    List<OrderItems> findAllByOrderId(Long orderId);
}
