package com.example.restfullapi.api.v1.mapper;

import com.example.restfullapi.api.v1.model.CategoryDTO;
import com.example.restfullapi.model.Category;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryMapperTest {


    @Test
    public void categoryToCategoryDTO() throws Exception {
        CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

        Category category = new Category(1L,"object");
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        Assert.assertEquals(category.getId(),categoryDTO.getId());
        Assert.assertEquals(category.getName(),categoryDTO.getName());

    }

}