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
import java.util.*;

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

    private static final String QUERY_FIND_BY_ID = "SELECT e.*, p.name AS position_name, ts.id AS time_slot_id, ts.start_time, ts.end_time, ts.is_booked\n" +
            "FROM employees e\n" +
            "         JOIN positions p ON e.position_id = p.id\n" +
            "         JOIN time_slots ts ON e.id = ts.employee_id\n" +
            "WHERE e.id = ?;";

    private static final String QUERY_FIND_ALL_WITH_AVAILABLE_TIME_SLOTS = "SELECT e.*, p.name AS position_name, ts.id AS time_slot_id, ts.start_time, ts.end_time, ts.is_booked\n" +
            "FROM employees e\n" +
            "         JOIN positions p ON e.position_id = p.id\n" +
            "         JOIN time_slots ts ON e.id = ts.employee_id\n" +
            "where ts.is_booked = FALSE";

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

//    @Override
//    public Optional<List<Employee>> findAll() {
//        List<Employee> employeeList = new ArrayList<>();
//
//        try (Connection conn = dataSource.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(QUERY_FIND_ALL);
//             ResultSet rs = stmt.executeQuery()) {
//
//            while (rs.next()) {
////                Employee employee = new Employee();
////                employee.setId(rs.getInt("id"));
////                employee.setName(rs.getString("name"));
////                employee.setSurname(rs.getString("surname"));
////                employee.setPhoneNumber(rs.getString("phone_number"));
////                employee.setPosition(Employee.Position.valueOf(rs.getString("position_name")));
////                employee.setEmail(rs.getString("email"));
////                employee.setAddress(rs.getString("address"));
////                employee.setBirthday(rs.getObject("birthday", LocalDate.class));
////                employee.setGender(rs.getString("gender"));
////                employee.setUrlImage(rs.getString("url_image"));
//                Employee employee = EmployeeMapperDB.mapRow(rs);
//
////                List<TimeSlot> timeSlotList = List.of(TimeSlotMapperDB.mapRow(rs));
////                while (rs.next()){
////                    timeSlotList.add(TimeSlotMapperDB.mapRow(rs));
////                }
////                employee.setTimeSlotList(timeSlotList);
//                employeeList.add(employee);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return Optional.ofNullable(employeeList);
//    }


//TODO : Костыль?? код с душком
@Override
public Optional<List<Employee>> findAll() {
    Map<Integer, Employee> employeeMap = new HashMap<>();

    try (Connection conn = dataSource.getConnection();
         PreparedStatement stmt = conn.prepareStatement(QUERY_FIND_ALL_WITH_AVAILABLE_TIME_SLOTS);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            int employeeId = rs.getInt("id");
            Employee employee = employeeMap.get(employeeId);
            if (employee == null) {
                employee = EmployeeMapperDB.mapRow(rs);
                employee.setTimeSlotList(new ArrayList<>()); // Инициализируем список TimeSlot
                employeeMap.put(employeeId, employee);
            }
            TimeSlot timeSlot = TimeSlotMapperDB.mapRow(rs);
            timeSlot.setEmployee(employee);
            employee.addTimeSlot(timeSlot);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return Optional.ofNullable(new ArrayList<>(employeeMap.values()));

}



    @Override
    public Optional<Employee> findById(int id) {
        Employee employee = null; // Инициализация переменной employee

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(QUERY_FIND_BY_ID)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                employee = EmployeeMapperDB.mapRow(rs);

                List<TimeSlot> timeSlotList = new ArrayList<>();
                do {
                    timeSlotList.add(TimeSlotMapperDB.mapRow(rs));
                } while (rs.next());

                final Employee finalEmployee = employee;
                timeSlotList.forEach(timeSlot -> timeSlot.setEmployee(finalEmployee));

                employee.setTimeSlotList(timeSlotList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(employee);
    }

}
