package com.example.supplier.api;

import com.example.supplier.model.Product;
import com.example.supplier.service.filter.FilterRequest;
import com.example.supplier.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping(consumes="application/json")
    @ResponseStatus(code=HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping
    public Page<Product> getAllProducts(@RequestParam Integer pageNumber,
                                        @RequestParam Integer pageSize) {
        return productService.getAllProducts(pageNumber, pageSize);
    }

    @GetMapping(path="/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PutMapping(path="/{id}", consumes="application/json")
    public Product putProduct(@PathVariable Long id,
                              @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping(path="/{id}")
    @ResponseStatus(code= HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @PostMapping(value = "/filter")
    public Page<Product> filter(@RequestBody FilterRequest filter) {
        return productService.filter(filter);
    }
}
