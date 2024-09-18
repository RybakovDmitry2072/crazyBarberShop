package org.example.crazybarbershop.repository;

import org.example.crazybarbershop.models.Category;
import org.example.crazybarbershop.models.User;

import java.util.List;

public interface CategoryRepository {

    List<Category> finfAll();

    Category findById(int id);

    void save(Category category);

    void delete(String id);

}
