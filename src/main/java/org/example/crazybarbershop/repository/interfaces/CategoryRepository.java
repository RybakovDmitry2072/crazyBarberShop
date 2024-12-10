package org.example.crazybarbershop.repository.interfaces;

import org.example.crazybarbershop.models.Category;

import java.util.List;

public interface CategoryRepository {

    List<Category> finfAll();

    Category findById(int id);

    void save(Category category);

    void delete(String id);

}
