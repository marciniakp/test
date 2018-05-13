package com.example.restfullapi.controller;

import com.example.restfullapi.api.v1.model.CategoryDTO;
import com.example.restfullapi.services.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CategoryControllerTest {

    public static final String CAT_1 = "cat1";
    @Mock
    CategoryService categoryService;
    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    public void getAllCategories() throws Exception {

        CategoryDTO cat1 = new CategoryDTO();
        cat1.setId(1L);
        cat1.setName("cat1");
        CategoryDTO cat2 = new CategoryDTO();
        cat2.setId(2L);
        cat2.setName("cat2");
        CategoryDTO cat3 = new CategoryDTO();
        cat3.setId(3L);
        cat3.setName("cat3");

        List<CategoryDTO> categories = Arrays.asList(cat1, cat2, cat3);

        when(categoryService.getAllCategories()).thenReturn(categories);

        mockMvc.perform(get(CategoryController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$.categories",hasSize(3)));
    }

    @Test
    public void getCategory() throws Exception {

        CategoryDTO cat1 = new CategoryDTO();
        cat1.setId(1L);
        cat1.setName(CAT_1);

        when(categoryService.getCategoryByName(anyString())).thenReturn(cat1);

        mockMvc.perform(get(CategoryController.BASE_URL + "/cat1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",equalTo(CAT_1)));
    }

}