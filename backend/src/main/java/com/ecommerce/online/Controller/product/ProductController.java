package com.ecommerce.online.Controller.product;


import com.ecommerce.online.Service.cache.CacheImpl;
import com.ecommerce.online.Service.product.ProductService;
import com.ecommerce.online.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequiredArgsConstructor
public class ProductController {

    @Autowired
    private final ProductService productService;

    @Autowired
    CacheImpl cacheImpl;


    @GetMapping("/allProducts")
    @PreAuthorize("hasAuthority('PRODUCT_READ')")
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
    @PreAuthorize("hasAuthority('PRODUCT_READ')")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {

        ProductDto product = productService.getProductById(id);

        return ResponseEntity.ok()
                .body(product);
    }

    @PostMapping("/products")
    @PreAuthorize("hasAuthority('PRODUCT_POST')")
    public void createProduct(@RequestBody ProductDto productDto)
    {
        productService.createProduct(productDto);
    }

    @PutMapping ("/products")
    @PreAuthorize("hasAuthority('PRODUCT_PUT')")
    public void updateProduct(@RequestBody ProductDto productDto)
    {
        productService.updateProduct(productDto);
    }

    @DeleteMapping("/products/{id}")
    @PreAuthorize("hasAuthority('PRODUCT_DELETE')")
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