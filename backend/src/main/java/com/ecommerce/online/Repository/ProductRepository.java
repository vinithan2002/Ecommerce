package com.ecommerce.online.Repository;

import com.ecommerce.online.Entity.Product;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>
{

    @Query(
            """
                    select p from Product p where
                    	(:categoryId IS NULL OR p.categoryId = :categoryId)
                        AND
                        (:minPrice IS NULL OR p.price >= :minPrice)
                        AND
                        (:maxPrice Is NULL OR p.price <= :maxPrice)
                        AND
                        (:brand IS NULL OR p.brand = :brand)
                        AND
                        (p.isActive = true)
                    """
    )
    List<Product> findProducts(
            @Param("categoryId") Long categoryId,
            @Param("minPrice") Long minPrice,
            @Param("maxPrice") Long maxPrice,
            @Param("brand") String brand,
            Sort sort);

    Product findByProductIdAndIsActiveTrue(Long id);

    Product findAllByProductId(Long productId);
}
