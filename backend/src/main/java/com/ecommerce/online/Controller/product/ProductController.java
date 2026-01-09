package com.ecommerce.online.Controller.product;


import com.ecommerce.online.Service.cache.CacheImpl;
import com.ecommerce.online.Service.product.ProductService;
import com.ecommerce.online.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.data.domain.Sort;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.concurrent.TimeUnit;


@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Autowired
    CacheImpl cacheImpl;

    @GetMapping("/products")
    public List<ProductDto> findProucts(
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Long minPrice,
            @RequestParam(required = false) Long maxPrice,
            @RequestParam(required = false) String brand,
            @RequestParam(defaultValue = "productId") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir )
    {
        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();
         return productService.findProducts(categoryId,minPrice,maxPrice,brand,sort);
    }

    @GetMapping("/products/{id}")
    public ProductDto getProduct(@PathVariable Long id)
    {
//        return ResponseEntity.ok()
//                .cacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES).cachePublic())
//                .body(productService.getProductById(id));
        return productService.getProductById(id);
    }

    @PostMapping("/products")
    public void createProduct(@RequestBody ProductDto productDto)
    {
        productService.createProduct(productDto);
    }

    @PutMapping ("/products")
    public void updateProduct(@RequestBody ProductDto productDto)
    {
        productService.updateProduct(productDto);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id)
    {
        productService.deleteProduct(id);
    }

    @GetMapping("/cacheDetails/{cacheName}")
    public void showCache(@PathVariable String cacheName)
    {
        cacheImpl.showCache(cacheName);
    }
}