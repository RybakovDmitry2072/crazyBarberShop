package org.example.crazybarbershop.repository.iml;

import org.example.crazybarbershop.map.UserMapperDB;
import org.example.crazybarbershop.models.User;
import org.example.crazybarbershop.repository.interfaces.UserRepository;
import java.sql.*;
import java.util.Optional;
import javax.sql.DataSource;

public class UserRepositoryIml implements UserRepository {

//    private static final String QUERY_DELETE = "DELETE FROM \"user\" WHERE login = ?";

    private static final String QUERY_SAVE = "INSERT INTO \"users\" (name, surname, login, phone_number, email, password, birthday, gender, role_id) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";

//    private static final String QUERY_FIND_ALL = "SELECT * FROM \"user\"";

//    private static final String QUERY_BY_LOGIN = "SELECT * FROM \"users\" WHERE login = ?";

    private static final String QUERY_BY_LOGIN = "select *, r.role_name as role_name from users u " +
            "join roles r on u.role_id = r.id " +
            "where u.email = \'yroboros2020@mail.ru\'";

//    private static final String QUERY_BY_EMAIL = "SELECT * FROM \"user\" WHERE email = ?";

    private final DataSource dataSource;

    public UserRepositoryIml(DataSource dataSource) {
        this.dataSource = dataSource;
    }

//    @Override
//    public void delete(String login) {
//
//        try (Connection conn = dataSource.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(QUERY_DELETE)) {
//
//            stmt.setString(1, login);
//            stmt.executeUpdate();
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

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
            e.printStackTrace();
        }
    }

//    @Override
//    public Optional<List<User>> findAll() {
//        List<User> users = new ArrayList<>();
//
//        try (Connection conn = dataSource.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(QUERY_FIND_ALL);
//             ResultSet rs = stmt.executeQuery()) {
//
//            while (rs.next()) {
//                users.add(UserMapperBD.mapRow(rs));
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return Optional.of(users);
//    }

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
            e.printStackTrace();
        }

        return Optional.ofNullable(user);

    }

//    @Override
//    public Optional<User> findByEmail(String email) {
//        User user = null;
//
//        try (Connection conn = dataSource.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(QUERY_BY_EMAIL)) {
//
//            stmt.setString(1, email);
//            ResultSet rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                user = UserMapperBD.mapRow(rs);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return Optional.of(user);
//    }

}
