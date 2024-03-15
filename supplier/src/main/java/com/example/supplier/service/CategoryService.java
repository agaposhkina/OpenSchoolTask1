package com.example.supplier.service;

import com.example.supplier.model.Category;

public interface CategoryService {
    Category createCategory(Category category);

    Iterable<Category> getAllCategories();

    Category getCategoryById(Long id);

    Category updateCategory(Long id, Category category);

    void deleteCategory(Long id);
}
