package com.example.supplier.api;

import com.example.supplier.model.Product;
import com.example.supplier.service.filter.FilterRequest;
import com.example.supplier.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Продукты")
@RestController
@RequestMapping(path = "/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @Operation(summary = "Создать новый продукт")
    @PostMapping(consumes="application/json")
    @ResponseStatus(code=HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @Operation(summary = "Получить список всех продуктов")
    @GetMapping
    public Page<Product> getAllProducts(@RequestParam Integer pageNumber,
                                        @RequestParam Integer pageSize) {
        return productService.getAllProducts(pageNumber, pageSize);
    }

    @Operation(summary = "Получить информацию о продукте по его идентификатору")
    @GetMapping(path="/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @Operation(summary = "Обновить информацию о продукте")
    @PutMapping(path="/{id}", consumes="application/json")
    public Product putProduct(@PathVariable Long id,
                              @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @Operation(summary = "Удалить продукт по его идентификатору")
    @DeleteMapping(path="/{id}")
    @ResponseStatus(code= HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }

    @Operation(summary = "Получить список продуктов, удовлетворяющих условиям примененного фильтра")
    @PostMapping(value = "/filter")
    public Page<Product> filter(@Parameter(description = "Параметры фильтрации по полям продукта и связанной категории")
            @RequestBody FilterRequest filter) {
        return productService.filter(filter);
    }
}
