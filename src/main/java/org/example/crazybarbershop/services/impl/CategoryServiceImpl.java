package org.example.crazybarbershop.services.impl;

import lombok.AllArgsConstructor;
import org.example.crazybarbershop.FactoryDto.CategoryDtoFactory;
import org.example.crazybarbershop.FactoryDto.EmployeeDtoFactory;
import org.example.crazybarbershop.dto.CategoryDto;
import org.example.crazybarbershop.models.Category;
import org.example.crazybarbershop.models.Employee;
import org.example.crazybarbershop.repository.iml.CategoryRepositoryImpl;
import org.example.crazybarbershop.repository.interfaces.CategoryRepository;
import org.example.crazybarbershop.services.interfaces.CategoryService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Override
    public CategoryDto getCategoryById(int id) {
        return null;
    }

    @Override
    public List<CategoryDto> getAllCatygory() {

        Optional<List<Category>> categoryList = categoryRepository.finfAll();
        try {
            return categoryList.get().stream()
                    .map(CategoryDtoFactory::factoryDto)
                    .collect(Collectors.toList());
        }catch (RuntimeException e) {
            throw new IllegalStateException("Not found categories", e);
        }
    }
}
