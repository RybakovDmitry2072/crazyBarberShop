package org.example.crazybarbershop.repository.iml;

import org.example.crazybarbershop.models.Emploee;
import org.example.crazybarbershop.models.TimeSlot;
import org.example.crazybarbershop.repository.EmploeeRepository;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmploeeRepositoryIml implements EmploeeRepository {

    private static final String QUERY_DELETE = "DELETE FROM emploee WHERE id = ?";

    private static final String QUERY_SAVE = "INSERT INTO emploee (name, surname, phone_number, position, email, address, birthday, gender, photo_url) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) " +
            "ON CONFLICT (id) DO UPDATE SET " +
            "name = EXCLUDED.name, surname = EXCLUDED.surname, phone_number = EXCLUDED.phone_number, " +
            "position = EXCLUDED.position, email = EXCLUDED.email, address = EXCLUDED.address, " +
            "birthday = EXCLUDED.birthday, gender = EXCLUDED.gender, photo_url = EXCLUDED.photo_url";

    private static final String QUERY_FIND_BY_ID = "SELECT * FROM emploee WHERE id = ?";

    private static final String QUERY_FIND_ALL = "SELECT * FROM emploee";

    private DataSource dataSource;

    public EmploeeRepositoryIml(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void delete(String id) {
        String query = QUERY_DELETE;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TimeSlot> findAllFreeTimeSlots() {
        // Реализуйте логику поиска свободных временных слотов
        return List.of(); // Возвращает пустой список по умолчанию
    }

    @Override
    public void save(Emploee emploee) {
        String query = QUERY_SAVE;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, emploee.getName());
            stmt.setString(2, emploee.getSurname());
            stmt.setString(3, emploee.getPhoneNumber());
            stmt.setString(4, emploee.getPosition());
            stmt.setString(5, emploee.getEmail());
            stmt.setString(6, emploee.getAddress());
            stmt.setObject(7, emploee.getBirthday());
            stmt.setString(8, emploee.getGender());
            stmt.setString(9, emploee.getUrlImage()); // Добавляем URL фото

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Emploee> findAll() {
        List<Emploee> emploeeList = new ArrayList<>();
        String query = QUERY_FIND_ALL;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Emploee emploee = new Emploee();
                emploee.setId(rs.getInt("id"));
                emploee.setName(rs.getString("name"));
                emploee.setSurname(rs.getString("surname"));
                emploee.setPhoneNumber(rs.getString("phone_number"));
                emploee.setPosition(rs.getString("position"));
                emploee.setEmail(rs.getString("email"));
                emploee.setAddress(rs.getString("address"));
                emploee.setBirthday(rs.getObject("birthday", LocalDate.class));
                emploee.setGender(rs.getString("gender"));
                emploee.setUrlImage(rs.getString("photo_url")); // Добавляем URL фото
                emploeeList.add(emploee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emploeeList;
    }

    @Override
    public Emploee findById(String id) {
        Emploee user = null;
        String query = QUERY_FIND_BY_ID;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                user = new Emploee();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setEmail(rs.getString("email"));
                user.setPosition(rs.getString("position"));
                user.setAddress(rs.getString("address"));
                user.setBirthday(rs.getObject("birthday", LocalDate.class));
                user.setGender(rs.getString("gender"));
                user.setUrlImage(rs.getString("photo_url")); // Добавляем URL фото
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
}
