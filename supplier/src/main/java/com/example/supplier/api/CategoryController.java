package com.example.supplier.api;

import com.example.supplier.model.Category;
import com.example.supplier.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Категории")
@RestController
@RequestMapping(path = "/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @Operation(summary = "Создать новую категорию")
    @PostMapping(consumes="application/json")
    @ResponseStatus(code=HttpStatus.CREATED)
    public Category createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }

    @Operation(summary = "Получить список всех категорий")
    @GetMapping
    public Iterable<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @Operation(summary = "Получить информацию о категории по ее идентификатору")
    @GetMapping(path="/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @Operation(summary = "Обновить информацию о категории")
    @PutMapping(path="/{id}", consumes="application/json")
    public Category putCategory(@PathVariable Long id,
                                @RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }

    @Operation(summary = "Удалить категорию по ее идентификатору")
    @DeleteMapping(path="/{id}")
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
