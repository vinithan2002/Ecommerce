package com.ecommerce.online.Service.product;

import com.ecommerce.online.dto.ProductDto;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface ProductService {
    List<ProductDto> findProducts(Long categoryId,Long minPrice, Long maxPrice, String brand, Sort sort);
    ProductDto getProductById(Long id);
    public void createProduct(ProductDto productDto);
    public void updateProduct(ProductDto productDto);
    public void deleteProduct(Long id);
}
