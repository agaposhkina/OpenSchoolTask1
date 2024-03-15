package com.example.supplier.service;

import com.example.supplier.exception.ResourceNotFoundException;
import com.example.supplier.model.Product;
import com.example.supplier.repository.ProductRepository;
import com.example.supplier.service.filter.FilterRequest;
import com.example.supplier.service.filter.FilterSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Page<Product> getAllProducts(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return productRepository.findAll(pageable);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No product found by id = " + id));
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        product.setId(id);
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<Product> filter(FilterRequest filter) {
        FilterSpecification<Product> specification = new FilterSpecification<>(filter);
        Pageable pageable = PageRequest.of(filter.getPageNumber(), filter.getPageSize());
        return productRepository.findAll(specification, pageable);
    }
}
