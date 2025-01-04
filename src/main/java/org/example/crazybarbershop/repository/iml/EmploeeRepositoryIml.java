package org.example.crazybarbershop.repository.iml;

import org.example.crazybarbershop.map.EmployeeMapperDB;
import org.example.crazybarbershop.map.TimeSlotMapperDB;
import org.example.crazybarbershop.models.Employee;
import org.example.crazybarbershop.models.TimeSlot;
import org.example.crazybarbershop.repository.interfaces.EmploeeRepository;
import javax.sql.DataSource;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class EmploeeRepositoryIml implements EmploeeRepository {

    private static final String QUERY_FIND_BY_ID = "SELECT e.*, p.name AS position_name, ts.id AS time_slot_id, ts.start_time, ts.end_time, ts.is_booked\n" +
            "FROM employees e\n" +
            "         JOIN positions p ON e.position_id = p.id\n" +
            "         JOIN time_slots ts ON e.id = ts.employee_id\n" +
            "WHERE e.id = ?;";

    private static final String QUERY_FIND_ALL_BARBER_WITH_AVAILABLE_TIME_SLOTS = "SELECT e.*, p.name AS position_name, ts.id AS time_slot_id, ts.start_time, ts.end_time, ts.is_booked\n" +
            "FROM employees e\n" +
            "         JOIN positions p ON e.position_id = p.id\n" +
            "         JOIN time_slots ts ON e.id = ts.employee_id\n" +
            "where ts.is_booked = FALSE and p.name = 'BARBER'";

    private static final String QUERY_FIND_ALL = "SELECT e.*, p.name AS position_name" +
            "FROM employees e\n" +
            "         JOIN time_slots ts ON e.id = ts.employee_id\n";

    private DataSource dataSource;

    public EmploeeRepositoryIml(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<List<Employee>> findAllBarber() {
        List<Employee> employeeList = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(QUERY_FIND_ALL_BARBER_WITH_AVAILABLE_TIME_SLOTS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Employee employee = Employee.builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("name"))
                        .experience(rs.getInt("experience"))
                        .about(rs.getString("about"))
                        .urlImage(rs.getString("url_image"))
                        .build();
                employeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(employeeList);
    }

    @Override
    public Optional<List<Employee>> findAllAvailableTimeSlots() {
        Map<Integer, Employee> employeeMap = new HashMap<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(QUERY_FIND_ALL_BARBER_WITH_AVAILABLE_TIME_SLOTS);
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
                timeSlot.setEmployeeId(employeeId);
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

                final int finalEmployee = employee.getId();
                timeSlotList.forEach(timeSlot -> timeSlot.setEmployeeId(finalEmployee));

                employee.setTimeSlotList(timeSlotList);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(employee);
    }

    @Override
    public void save(Employee employee) {
        String sql = "INSERT INTO employees (name, surname, phone_number, position_id, email, address, birthday, gender, url_image, about, experience) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getSurname());
            stmt.setString(3, employee.getPhoneNumber());
            stmt.setInt(4, employee.getPositionId(employee.getPosition()));
            stmt.setString(5, employee.getEmail());
            stmt.setString(6, employee.getAddress());
            stmt.setDate(7, Date.valueOf((employee.getBirthday())));
            stmt.setString(8, employee.getGender());
            stmt.setString(9, employee.getUrlImage());
            stmt.setString(10, employee.getAbout());
            stmt.setInt(11, employee.getExperience());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    employee.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error saving employee", e);
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting employee", e);
        }
    }

    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> employees = new ArrayList<>();
        String sql = QUERY_FIND_ALL;
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Employee employee = EmployeeMapperDB.mapRow(rs);
                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving employees", e);
        }
        return employees;
    }

    @Override
    public void update(Employee employee) {
        String sql = "UPDATE employees SET name = ?, surname = ?, phone_number = ?, position_id = ?, email = ?, address = ?, birthday = ?, gender = ?, url_image = ?, about = ?, experience = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getSurname());
            stmt.setString(3, employee.getPhoneNumber());
            stmt.setInt(4, employee.getPositionId(employee.getPosition()));
            stmt.setString(5, employee.getEmail());
            stmt.setString(6, employee.getAddress());
            stmt.setDate(7, java.sql.Date.valueOf((employee.getBirthday())));
            stmt.setString(8, employee.getGender());
            stmt.setString(9, employee.getUrlImage());
            stmt.setString(10, employee.getAbout());
            stmt.setInt(11, employee.getExperience());
            stmt.setInt(12, employee.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating employee", e);
        }
    }

}
