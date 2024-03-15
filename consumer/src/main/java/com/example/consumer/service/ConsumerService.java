package com.example.consumer.service;

import com.example.consumer.config.SupplierApiProperties;
import com.example.consumer.model.Category;
import com.example.consumer.model.Product;
import com.example.consumer.service.filter.FilterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class ConsumerService {
    private final SupplierApiProperties apiProps;
    private final RestTemplate restTemplate;

    public ResponseEntity<ConsumerPage<Product>> getProducts(MultiValueMap<String, String> queryParams) {
        String getProductsQuery = UriComponentsBuilder.fromHttpUrl(apiProps.getProducts())
                .queryParams(queryParams)
                .build()
                .toUriString();
        return restTemplate.exchange(getProductsQuery, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {});
    }

    public ResponseEntity<Iterable<Category>> getCategories() {
        return restTemplate.exchange(apiProps.getCategories(), HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {});
    }

    public ResponseEntity<Product> getProduct(Object... uriVariables) {
        return restTemplate.getForEntity(apiProps.getProduct(), Product.class, uriVariables);
    }

    public ResponseEntity<Category> getCategory(Object... uriVariables) {
        return restTemplate.getForEntity(apiProps.getCategory(), Category.class, uriVariables);
    }

    public ResponseEntity<ConsumerPage<Product>> filterProducts(FilterRequest filter) {
        return restTemplate.exchange(apiProps.getFilterProducts(), HttpMethod.POST, new HttpEntity<>(filter),
                new ParameterizedTypeReference<>() {});
    }

    public ResponseEntity<Product> createProduct(String name, String description, double price, long categoryId) {
        Category category = getCategory(categoryId).getBody();
        Product product = Product.builder()
                .name(name)
                .description(description)
                .price(price)
                .category(category)
                .build();
        return restTemplate.postForEntity(apiProps.getProducts(), product, Product.class);
    }

    public ResponseEntity<Category> createCategory(String name) {
        Category category = Category.builder()
                .name(name)
                .build();
        return restTemplate.postForEntity(apiProps.getCategories(), category, Category.class);
    }

    public ResponseEntity<Product> updateProduct(String name, String description, double price, long categoryId, Object... uriVariables) {
        Category category = getCategory(categoryId).getBody();
        Product product = Product.builder()
                .name(name)
                .description(description)
                .price(price)
                .category(category)
                .build();
        return restTemplate.exchange(apiProps.getProduct(), HttpMethod.PUT, new HttpEntity<>(product), Product.class, uriVariables);
    }

    public ResponseEntity<Category> updateCategory(String name, Object... uriVariables) {
        Category category = Category.builder()
                .name(name)
                .build();
        return restTemplate.exchange(apiProps.getCategory(), HttpMethod.PUT, new HttpEntity<>(category), Category.class, uriVariables);
    }

    public ResponseEntity<?> deleteProduct(Object... urlVariables) {
        return restTemplate.exchange(apiProps.getProduct(), HttpMethod.DELETE, null, HttpStatus.class, urlVariables);
    }

    public ResponseEntity<?> deleteCategory(Object... urlVariables) {
        return restTemplate.exchange(apiProps.getCategory(), HttpMethod.DELETE, null, HttpStatus.class, urlVariables);
    }
}
