package com.example.supplier.config;

import com.example.supplier.repository.CategoryRepository;
import com.example.supplier.repository.ProductRepository;
import com.example.supplier.service.CategoryService;
import com.example.supplier.service.CategoryServiceImpl;
import com.example.supplier.service.ProductService;
import com.example.supplier.service.ProductServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public CategoryService categoryService(CategoryRepository categoryRepository) {
        return new CategoryServiceImpl(categoryRepository);
    }

    @Bean
    public ProductService productService(ProductRepository productRepository) {
        return new ProductServiceImpl(productRepository);
    }
}
