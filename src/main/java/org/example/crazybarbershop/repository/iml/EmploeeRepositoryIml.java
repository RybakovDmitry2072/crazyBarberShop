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

    private DataSource dataSource;

    public EmploeeRepositoryIml(DataSource dataSource) {
        this.dataSource = dataSource;
    }

@Override
public Optional<List<Employee>> findAll() {
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

}
