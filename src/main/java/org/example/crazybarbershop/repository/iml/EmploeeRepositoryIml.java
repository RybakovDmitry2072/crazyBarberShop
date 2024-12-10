package org.example.crazybarbershop.repository.iml;

import org.example.crazybarbershop.map.EmployeeMapperDB;
import org.example.crazybarbershop.map.TimeSlotMapperDB;
import org.example.crazybarbershop.models.Employee;
import org.example.crazybarbershop.models.TimeSlot;
import org.example.crazybarbershop.repository.interfaces.EmploeeRepository;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class EmploeeRepositoryIml implements EmploeeRepository {

//    private static final String QUERY_DELETE = "DELETE FROM emploee WHERE id = ?";
//    private static final String QUERY_SAVE = "INSERT INTO emploee (name, surname, phone_number, position, email, address, birthday, gender, photo_url) " +
//            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) " +
//            "ON CONFLICT (id) DO UPDATE SET " +
//            "name = EXCLUDED.name, surname = EXCLUDED.surname, phone_number = EXCLUDED.phone_number, " +
//            "position = EXCLUDED.position, email = EXCLUDED.email, address = EXCLUDED.address, " +
//            "birthday = EXCLUDED.birthday, gender = EXCLUDED.gender, photo_url = EXCLUDED.photo_url";
//    private static final String QUERY_FIND_BY_ID = "SELECT * FROM emploee WHERE id = ?";
//    private static final String QUERY_FIND_ALL = "SELECT * FROM emploee";

    private static final String QUERY_FIND_BY_ID = "SELECT e.*, p.name as positions_name " +
            "from employees e " +
            "join positions p on e.position_id = p.id " +
            "where e.id = ?";

    private static final String QUERY_FIND_ALL = "SELECT e.*, p.name as positions_name from employees e " +
            "join positions p on e.position_id = p.id";

    private DataSource dataSource;

    public EmploeeRepositoryIml(DataSource dataSource) {
        this.dataSource = dataSource;
    }

//    @Override
//    public void delete(String id) {
//        String query = QUERY_DELETE;
//        try (Connection conn = dataSource.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//            stmt.setString(1, id);
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void save(Employee employee) {
//        String query = QUERY_SAVE;
//
//        try (Connection conn = dataSource.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(query)) {
//            stmt.setString(1, employee.getName());
//            stmt.setString(2, employee.getSurname());
//            stmt.setString(3, employee.getPhoneNumber());
//            stmt.setInt(4, employee.getPosition().getId());
//            stmt.setString(5, employee.getEmail());
//            stmt.setString(6, employee.getAddress());
//            stmt.setObject(7, employee.getBirthday());
//            stmt.setString(8, employee.getGender());
//            stmt.setString(9, employee.getUrlImage()); // Добавляем URL фото
//
//            stmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public Optional<List<Employee>> findAll() {
        List<Employee> employeeList = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(QUERY_FIND_ALL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setName(rs.getString("name"));
                employee.setSurname(rs.getString("surname"));
                employee.setPhoneNumber(rs.getString("phone_number"));
                employee.setPosition(Employee.Position.valueOf(rs.getString("positions_name")));
                employee.setEmail(rs.getString("email"));
                employee.setAddress(rs.getString("address"));
                employee.setBirthday(rs.getObject("birthday", LocalDate.class));
                employee.setGender(rs.getString("gender"));
                employee.setUrlImage(rs.getString("url_image"));
                employeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(employeeList);
    }

    @Override
    public Optional<Employee> findById(String id) {
        Employee employee = null;

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(QUERY_FIND_BY_ID)) {

            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                employee = EmployeeMapperDB.mapRow(rs);

                //TODO : этого ли класса ответственность за создания timeSlot??? + маленькое сцепление !!!
                List<TimeSlot> timeSlotList = List.of(TimeSlotMapperDB.mapRow(rs));
                while (rs.next()){
                    timeSlotList.add(TimeSlotMapperDB.mapRow(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(employee);
    }
}
