package org.example.crazybarbershop.repository.interfaces;

import org.example.crazybarbershop.models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    Optional<List<Category>> finfAll();

    Optional<Category> findById(int id);

    void save(Category category);

    void delete(String id);

}
