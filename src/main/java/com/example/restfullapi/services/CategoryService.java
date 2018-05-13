package com.example.restfullapi.services;

import com.example.restfullapi.api.v1.model.CategoryDTO;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;

public interface CategoryService {

    @Required
    List<CategoryDTO> getAllCategories();
    CategoryDTO getCategoryByName(String name);
}
