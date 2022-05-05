package com.blog.services;

import com.blog.dtos.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto getCategoryById(Integer categoryId);
    List<CategoryDto> getAllCategories();
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
    void deleteCategory(Integer categoryId);
}
