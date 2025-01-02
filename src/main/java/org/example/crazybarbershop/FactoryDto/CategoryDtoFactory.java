package org.example.crazybarbershop.FactoryDto;

import org.example.crazybarbershop.dto.CategoryDto;
import org.example.crazybarbershop.models.Category;

public class CategoryDtoFactory {

    public static CategoryDto factoryDto(Category category){
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .price(category.getPrice())
                .urlImg(category.getUrlImg())
                .build();
    }

}
