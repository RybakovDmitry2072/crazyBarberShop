package org.example.crazybarbershop.repository.iml;

import org.example.crazybarbershop.models.Category;
import org.example.crazybarbershop.repository.CategoryRepository;

import javax.sql.DataSource;
import java.util.List;

public class CategoryRepositoryImpl implements CategoryRepository {

    private DataSource dataSource;

    public CategoryRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<Category> finfAll() {
        return List.of();
    }

    @Override
    public Category findById(int id) {
        return null;
    }

    @Override
    public void save(Category category) {

    }
}
