package org.example.crazybarbershop.services.impl;

import lombok.AllArgsConstructor;
import org.example.crazybarbershop.FactoryDto.CategoryDtoFactory;
import org.example.crazybarbershop.dto.CategoryDto;
import org.example.crazybarbershop.models.Category;
import org.example.crazybarbershop.repository.CategoryRepository;
import org.example.crazybarbershop.services.interfaces.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Override
    public CategoryDto getCategoryById(int id) {
        return CategoryDtoFactory.factory(categoryRepository.findById(id));
    }

    @Override
    public List<CategoryDto> getAllCatygory() {
        List<Category> categories = categoryRepository.finfAll();
        return categories.stream()
                .map(CategoryDtoFactory::factory)
                .collect(Collectors.toList());
    }
}
