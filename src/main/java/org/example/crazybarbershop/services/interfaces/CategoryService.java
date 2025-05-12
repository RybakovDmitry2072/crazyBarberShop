package org.example.crazybarbershop.services.interfaces;

import org.example.crazybarbershop.dto.CategoryDto;
import org.example.crazybarbershop.models.Category;

import java.util.List;

public interface CategoryService {

    CategoryDto getCategoryById(int id);

    List<CategoryDto> getAllCatygory();

    void delete(int id);

    void update(Category category);

    void save(Category category);

}
