package org.example.crazybarbershop.repository.iml;

import org.example.crazybarbershop.models.Category;
import org.example.crazybarbershop.repository.interfaces.CategoryRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryRepositoryImpl implements CategoryRepository {

    private static final String QUERY_DELETE = "DELETE FROM categories WHERE id = ?";
    private static final String QUERY_FIND_ALL = "SELECT * FROM categories";
    private static final String QUERY_FIND_BY_ID = "SELECT * FROM categories WHERE id = ?";
    private static final String QUERY_SAVE = "INSERT INTO categories (name, price, image_url) VALUES (?, ?, ?) " +
            "ON CONFLICT (id) DO UPDATE SET name = EXCLUDED.name, price = EXCLUDED.price, image_url = EXCLUDED.image_url";

    private DataSource dataSource;

    public CategoryRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void delete(String id) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(QUERY_DELETE)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<List<Category>> finfAll() {
        List<Category> categoryList = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(QUERY_FIND_ALL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Category category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setPrice(rs.getInt("price"));
                System.out.println( category);
                categoryList.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(categoryList);
    }

    @Override
    public Optional<Category> findById(int id) {
        Category category = null;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(QUERY_FIND_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                category = new Category();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(category);
    }

    @Override
    public void save(Category category) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(QUERY_SAVE)) {
            stmt.setString(1, category.getName());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}