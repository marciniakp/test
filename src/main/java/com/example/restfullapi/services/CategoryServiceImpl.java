package com.example.restfullapi.services;


import com.example.restfullapi.api.v1.mapper.CategoryMapper;
import com.example.restfullapi.api.v1.model.CategoryDTO;
import com.example.restfullapi.model.Category;
import com.example.restfullapi.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<CategoryDTO> allCategories = categoryRepository.findAll()
                .stream()
                .map(categoryMapper::categoryToCategoryDTO)
                .collect(Collectors.toList());

        return allCategories;
    }

    @Override
    public CategoryDTO getCategoryByName(String name) {
        Category category = categoryRepository.findByName(name).get();
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);
        return categoryDTO;
    }
}
