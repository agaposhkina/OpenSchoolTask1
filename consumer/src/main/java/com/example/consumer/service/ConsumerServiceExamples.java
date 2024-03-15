package com.example.consumer.service;

import com.example.consumer.service.filter.Filter;
import com.example.consumer.service.filter.FilterRequest;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.function.Supplier;

import static com.example.consumer.service.filter.Filter.Operator.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConsumerServiceExamples {
    private final ConsumerService service;

    @PostConstruct
    public void init() {
        log.info("Getting products and categories");
        MultiValueMap<String, String> getProductsQueryParams = new LinkedMultiValueMap<>();
        getProductsQueryParams.add("pageNumber", "0");
        getProductsQueryParams.add("pageSize", "20");
        processRequest(()-> service.getProducts(getProductsQueryParams));
        processRequest(service::getCategories);
        processRequest(()-> service.getProduct( 1L));
        processRequest(()-> service.getCategory( 1L));

        log.info("Filter products by price > 1000 AND category.name LIKE %ANSP%:");
        FilterRequest filterRequest = new FilterRequest(0, 20);
        filterRequest.add(Filter.builder().key("price").operator(GREATER).value(1000.0).build());
        filterRequest.add(Filter.builder().key("name").joinTable("category").operator(MATCH).value("ANSP").build());
        processRequest(()-> service.filterProducts(filterRequest));

        log.info("Search products by description = RABBIT WITH CARROT:");
        FilterRequest searchRequest = new FilterRequest(0, 20);
        searchRequest.add(Filter.builder().key("description").operator(EQUAL).value("RABBIT WITH CARROT").build());
        processRequest(()-> service.filterProducts(searchRequest));

        log.info("Create new product and category:");
        processRequest(()-> service.createProduct("new product", "new product desc", 1200.0, 1L));
        processRequest(()-> service.createCategory("new category"));

        log.info("Update product and category:");
        processRequest(()-> service.updateProduct("updated product", "updated product desc",
                2500.0, 7L, 7L));
        processRequest(()-> service.updateCategory("updated category", 7L));

        log.info("Delete product id={} and category id={}", 7L, 7L);
        processRequest(()-> service.deleteProduct(7L));
        log.info("Products after delete {}: {}", 7L, service.getProducts(getProductsQueryParams));
        processRequest(()-> service.deleteCategory(7L));
        log.info("Categories after delete {}: {}", 7L, service.getCategories());

        log.info("Filter by incorrect field name");
        FilterRequest incorrectFilter = new FilterRequest(0, 20);
        incorrectFilter.add(Filter.builder().key("ERROR").operator(GREATER).value(1000.0).build());
        processRequest(()-> service.filterProducts(incorrectFilter));
        log.info("Find product with incorrect id");
        processRequest(()-> service.getProduct( 111111L));
    }

    private static void processRequest(Supplier<ResponseEntity<?>> requestSupplier) {
        try {
            log.info("Response received: {}", requestSupplier.get());
        }
        catch (Exception e) {
            log.error("Exception occurred: ", e);
        }
    }
}
