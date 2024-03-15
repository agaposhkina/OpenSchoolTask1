package com.example.supplier.service;

import com.example.supplier.model.Product;
import com.example.supplier.service.filter.FilterRequest;
import org.springframework.data.domain.Page;

public interface ProductService {
    Product createProduct(Product product);

    Page<Product> getAllProducts(Integer pageNumber, Integer pageSize);

    Product getProductById(Long id);

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);

    Page<Product> filter(FilterRequest filter);
}
