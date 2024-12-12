package org.example.crazybarbershop.repository.iml;

import org.example.crazybarbershop.models.Appointment;
import org.example.crazybarbershop.repository.interfaces.AppointmentRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class AppointmentRepositoryImpl implements AppointmentRepository {

    private static final String QUERY_SAVE = "INSERT INTO appointments(category_id, employee_id, time_slot_id) values(?, ?, ?)";

    private DataSource dataSource;

    public AppointmentRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void save(Appointment appointment) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement stmt = connection.prepareStatement(QUERY_SAVE)) {

            stmt.setInt(1, appointment.getCategoryId());
            stmt.setInt(2, appointment.getEmployeeId());
            stmt.setInt(3, appointment.getTimeSlotId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Appointment> findAll() {
        return List.of();
    }

    @Override
    public Appointment findById(String id) {
        return null;
    }
}
