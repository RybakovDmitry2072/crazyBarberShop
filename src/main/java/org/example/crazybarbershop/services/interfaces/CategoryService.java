package org.example.crazybarbershop.services.interfaces;

import org.example.crazybarbershop.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    CategoryDto getCategoryById(int id);

    List<CategoryDto> getAllCatygory();

}
