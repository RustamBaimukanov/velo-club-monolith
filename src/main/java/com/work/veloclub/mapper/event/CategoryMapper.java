package com.work.veloclub.mapper.event;

import com.work.veloclub.model.event.category.Category;
import com.work.veloclub.model.event.category.CategoryDTO;

public class CategoryMapper {

    public static CategoryDTO mapToCategoryDTO(Category category){
        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .type(category.getType())
                .numericProperty(category.getNumericProperty())
                .descriptionProperty(category.getDescriptionProperty())
                .build();
    }
}
