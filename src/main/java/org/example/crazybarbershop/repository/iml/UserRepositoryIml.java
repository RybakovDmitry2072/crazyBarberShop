package org.example.crazybarbershop.repository.iml;

import org.example.crazybarbershop.Exceptions.DbException;
import org.example.crazybarbershop.map.UserMapperDB;
import org.example.crazybarbershop.models.User;
import org.example.crazybarbershop.repository.interfaces.UserRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryIml implements UserRepository {

    private static final String QUERY_SAVE = "INSERT INTO \"users\" (name, surname, login, phone_number, email, password, birthday, gender) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";

    private static final String QUERY_BY_LOGIN = "SELECT *, r.role_name AS role_name FROM users u " +
            "JOIN roles r ON u.role_id = r.id " +
            "WHERE u.login = ?";

    private static final String QUERY_FIND_ALL = "SELECT *, r.role_name AS role_name FROM users u " +
            "JOIN roles r ON u.role_id = r.id";

    private static final String QUERY_UPDATE = "UPDATE users SET name = ?, surname = ?, login = ?, phone_number = ?, email = ?, birthday = ?, gender = ?, role_id = ? WHERE id = ?";

    private static final String QUERY_DELETE = "DELETE FROM users WHERE id = ?";

    private static final String QUERY_FIND_BY_ID = "SELECT *, r.role_name AS role_name FROM users u " +
            "JOIN roles r ON u.role_id = r.id " +
            "WHERE u.id = ?";

    private final DataSource dataSource;

    public UserRepositoryIml(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(User user) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(QUERY_SAVE)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getLogin());
            stmt.setString(4, user.getPhoneNumber());
            stmt.setString(5, user.getEmail());
            stmt.setString(6, user.getPassword());
            stmt.setObject(7, user.getBirthday());
            stmt.setString(8, user.getGender());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DbException("Failed to save user: " + e.getMessage(), e);
        }
    }

    @Override
    public User findById(int id) {
        User user = null;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(QUERY_FIND_BY_ID)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = UserMapperDB.mapRow(rs);
            }
        } catch (SQLException e) {
            throw new DbException("Failed to find user by ID: " + e.getMessage(), e);
        }

        return user;
    }

    @Override
    public Optional<User> findByLogin(String login) {
        User user = null;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(QUERY_BY_LOGIN)) {
            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = UserMapperDB.mapRow(rs);
            }
        } catch (SQLException e) {
            throw new DbException("Failed to find user by login: " + e.getMessage(), e);
        }

        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(QUERY_FIND_ALL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User user = UserMapperDB.mapRow(rs);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DbException("Failed to find all users: " + e.getMessage(), e);
        }
        return users;
    }

    @Override
    public void update(User user) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(QUERY_UPDATE)) {

            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getLogin());
            stmt.setString(4, user.getPhoneNumber());
            stmt.setString(5, user.getEmail());
            stmt.setDate(6, Date.valueOf(user.getBirthday()));
            stmt.setString(7, user.getGender());
            stmt.setInt(8, user.getRoleId(user.getRole()));
            stmt.setInt(9, user.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Failed to update user: " + e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(QUERY_DELETE)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DbException("Failed to delete user: " + e.getMessage(), e);
        }
    }
}
