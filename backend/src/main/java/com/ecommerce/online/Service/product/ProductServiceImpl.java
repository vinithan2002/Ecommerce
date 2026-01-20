package com.ecommerce.online.Service.product;

import com.ecommerce.online.Entity.Product;
import com.ecommerce.online.Repository.ProductRepository;
import com.ecommerce.online.dto.ProductDto;
import com.ecommerce.online.exception.ProductNotFoundException;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;


//    @PostConstruct
//    public void init() throws Exception {
//        System.out.println("After Property Set");
//    }
//
//    @PreDestroy
//    public void destroy() throws Exception {
//        System.out.println("Destroyed");
//    }

    public List<ProductDto> findProducts(Long categoryId, Long minPrice, Long maxPrice, String brand, Sort sort) {

        List<Product> products = productRepository.findProducts(categoryId, minPrice,  maxPrice,  brand, sort);
        List<ProductDto> productDtoList= products.stream()
                .map(product -> new ProductDto(
                product.getProductId(),
                product.getProductName(),
                product.getDescription(),
                product.getPrice(),
                product.getDiscountPrice(),
                product.getCurrency(),
                product.getQuantityInStock(),
                product.getIsActive(),
                product.getCategoryId(),
                product.getBrand(),
                product.getCreatedAt(),
                product.getUpdatedAt())).toList();
        return productDtoList;
    }


    //@Cacheable(value = "product", key = "#id")
    public ProductDto getProductById(Long id) {
        System.out.println("inside method");
        Product product = productRepository.findByProductIdAndIsActiveTrue(id);
        if (product == null) {
            throw new ProductNotFoundException(
                    "Product not found with id: " + id
            );
        }
        return mapToDto(product);
    }

    public ProductDto mapToDto(Product product)
    {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(product.getProductId());
        productDto.setProductName(product.getProductName());
        productDto.setDescription(product.getDescription());
        productDto.setCurrency(product.getCurrency());
        productDto.setQuantityInStock(product.getQuantityInStock());
        productDto.setIsActive(product.getIsActive());
        productDto.setCategoryId(product.getCategoryId());
        productDto.setCreatedAt(product.getCreatedAt());
        productDto.setUpdatedAt(product.getUpdatedAt());
        productDto.setPrice(product.getPrice());
        productDto.setDiscountPrice(product.getDiscountPrice());
        productDto.setBrand(product.getBrand());

        return productDto;
    }


    public void createProduct(ProductDto productDto)
    {
        Product product =  modelMapper.map(productDto, Product.class);
        productRepository.save(product);
    }


   //@CachePut(value = "product", key = "#id")
    @Transactional
    public void updateProduct(ProductDto productDto)
    {
        Product product =  modelMapper.map(productDto, Product.class);
        productRepository.save(product);
    }

    @Transactional
    @CacheEvict(value ="product", key = "#id")
    public void deleteProduct(Long id)
    {
        Product product = productRepository.findById(id).get();
        product.setIsActive(false);
        productRepository.save(product);
    }

}
