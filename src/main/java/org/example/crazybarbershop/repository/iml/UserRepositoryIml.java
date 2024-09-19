package org.example.crazybarbershop.repository.iml;

import org.example.crazybarbershop.map.UserMapperBD;
import org.example.crazybarbershop.models.User;
import org.example.crazybarbershop.repository.UserRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class UserRepositoryIml implements UserRepository {

    private static final String QUERY_DELETE = "DELETE FROM \"user\" WHERE login = ?";

    private static final String QUERY_SAVE = "INSERT INTO \"user\" (name, surname, login, phone_number, email, password, birthday, gender) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?) " +
            "ON CONFLICT (login) DO UPDATE SET " +
            "name = EXCLUDED.name, surname = EXCLUDED.surname, phone_number = EXCLUDED.phone_number, " +
            "email = EXCLUDED.email, password = EXCLUDED.password, birthday = EXCLUDED.birthday, " +
            "gender = EXCLUDED.gender";

    private static final String QUERY_FIND_ALL = "SELECT * FROM \"user\"";

    private static final String QUERY_BY_LOGIN = "SELECT * FROM \"user\" WHERE login = ?";

    private final DataSource dataSource;

    public UserRepositoryIml(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void delete(String login) {
        String query = QUERY_DELETE;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, login);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(User user) {
        String query = QUERY_SAVE;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

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
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String query = QUERY_FIND_ALL;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                users.add(UserMapperBD.mapRow(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public User findByLogin(String login) {
        User user = null;
        String query = QUERY_BY_LOGIN;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, login);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = UserMapperBD.mapRow(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
