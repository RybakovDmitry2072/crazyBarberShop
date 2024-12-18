package org.example.crazybarbershop.repository.iml;

import org.example.crazybarbershop.models.Appointment;
import org.example.crazybarbershop.repository.interfaces.AppointmentRepository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AppointmentRepositoryImpl implements AppointmentRepository {

    private static final String QUERY_SAVE = "INSERT INTO appointments(category_id, employee_id, time_slot_id) values(?, ?, ?)";

    private static final String QUERY_FIND_APPOINTMENTS_BY_USER_ID = "SELECT id, user_id, category_id, employee_id, time_slot_id, status FROM appointments WHERE user_id = ?";

    private static final String QUERY_FIND_BY_ID = "SELECT * FROM appointments WHERE id = ?";

    private DataSource dataSource;

    public AppointmentRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Appointment> findById(int id) {
        Appointment appointment = null;
        try (Connection connection = dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(QUERY_FIND_BY_ID)){
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                appointment = new Appointment();
                appointment.setId(rs.getInt("id"));
                appointment.setUserId(rs.getInt("user_id"));
                appointment.setStatus(rs.getString("status"));
                appointment.setCategoryId(rs.getInt("category_id"));
                appointment.setTimeSlotId(rs.getInt("time_slot_id"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(appointment);
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
    public Optional<List<Appointment>> findAppointmentByUserId(int id) {
        List<Appointment> appointmentList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY_FIND_APPOINTMENTS_BY_USER_ID)) {

            preparedStatement.setInt(1, id);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    Appointment appointment = new Appointment();
                    appointment.setId(rs.getInt("id"));
                    appointment.setUserId(rs.getInt("user_id"));
                    appointment.setCategoryId(rs.getInt("category_id"));
                    appointment.setEmployeeId(rs.getInt("employee_id"));
                    appointment.setTimeSlotId(rs.getInt("time_slot_id"));
                    appointment.setStatus(rs.getString("status"));
                    appointmentList.add(appointment);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(appointmentList);
    }

}
