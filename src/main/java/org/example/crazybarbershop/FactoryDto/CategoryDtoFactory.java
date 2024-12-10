package org.example.crazybarbershop.FactoryDto;

import org.example.crazybarbershop.dto.CategoryDto;
import org.example.crazybarbershop.models.Category;

public class CategoryDtoFactory {

    public static CategoryDto factory(Category category){

        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .build();

    }
}
