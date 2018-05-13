package com.example.restfullapi.services;

import com.example.restfullapi.api.v1.mapper.CategoryMapper;
import com.example.restfullapi.api.v1.model.CategoryDTO;
import com.example.restfullapi.model.Category;
import com.example.restfullapi.repositories.CategoryRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class CategoryServiceTest {

    public static final String NAME = "name";
    CategoryService categoryService;
    @Mock
    CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository, CategoryMapper.INSTANCE);
    }

    @Test
    public void getAllCategories() throws Exception {

        List<Category> categoryList = Arrays.asList(new Category(), new Category(), new Category());

        when(categoryRepository.findAll()).thenReturn(categoryList);

        List<CategoryDTO> allCategories = categoryService.getAllCategories();

        assertEquals(3,allCategories.size());

    }

    @Test
    public void getCategoryByName() throws Exception {

        Category category = new Category(1L, NAME);

        when(categoryRepository.findByName(anyString())).thenReturn(Optional.of(category));

        CategoryDTO categoryByName = categoryService.getCategoryByName(NAME);

        assertEquals(Long.valueOf(1),categoryByName.getId());
        assertEquals(NAME,categoryByName.getName());
    }

}