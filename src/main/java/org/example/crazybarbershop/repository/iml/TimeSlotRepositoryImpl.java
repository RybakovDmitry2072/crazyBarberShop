package org.example.crazybarbershop.repository.iml;

import org.example.crazybarbershop.models.TimeSlot;
import org.example.crazybarbershop.repository.interfaces.TimeSlotRepository;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TimeSlotRepositoryImpl implements TimeSlotRepository {

    private static final String QUERY_NEW_FLAG = "UPDATE time_slots SET is_booked = ? WHERE id = ?";

    private static final String QUERY_TIME_SLOT_BY_ID = "SELECT * FROM time_slots WHERE id = ?";

    private DataSource dataSource;

    public TimeSlotRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void save(TimeSlot timeSlot) {
        String sql = "INSERT INTO time_slots (employee_id, start_time, end_time, is_booked) VALUES (?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, timeSlot.getEmployeeId());
            stmt.setTimestamp(2, Timestamp.valueOf(timeSlot.getStartTime()));
            stmt.setTimestamp(3, Timestamp.valueOf(timeSlot.getEndTime()));
            stmt.setBoolean(4, timeSlot.getIsBooked());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error adding time slot", e);
        }
    }

    @Override
    public Optional<TimeSlot> getTimeSlotByid(int timeSlotId) {
        TimeSlot timeSlot = null;
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_TIME_SLOT_BY_ID);
            ) {
            preparedStatement.setInt(1, timeSlotId);

            try (ResultSet rs = preparedStatement.executeQuery()){
                if (rs.next()) {
                    timeSlot = new TimeSlot();
                    timeSlot.setEmployeeId(rs.getInt("employee_id"));
                    timeSlot.setId(rs.getInt("id"));
                    timeSlot.setStartTime(rs.getTimestamp("start_time").toLocalDateTime());
                    timeSlot.setEndTime(rs.getTimestamp("end_time").toLocalDateTime());
                    timeSlot.setBooked(rs.getBoolean("is_booked"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(timeSlot);
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM time_slots WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting time slot", e);
        }
    }

    @Override
    public void update(TimeSlot timeSlot) {
        String sql = "UPDATE time_slots SET employee_id = ?, start_time = ?, end_time = ?, is_booked = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, timeSlot.getEmployeeId());
            stmt.setTimestamp(2, Timestamp.valueOf(timeSlot.getStartTime()));
            stmt.setTimestamp(3, Timestamp.valueOf(timeSlot.getEndTime()));
            stmt.setBoolean(4, timeSlot.getIsBooked());
            stmt.setInt(5, timeSlot.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating time slot", e);
        }
    }

    @Override
    public List<TimeSlot> getAllTimeSlots() {
        List<TimeSlot> timeSlots = new ArrayList<>();
        String sql = "SELECT * FROM time_slots";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                TimeSlot timeSlot = new TimeSlot();
                timeSlot.setId(rs.getInt("id"));
                timeSlot.setEmployeeId(rs.getInt("employee_id"));
                timeSlot.setStartTime(rs.getTimestamp("start_time").toLocalDateTime());
                timeSlot.setEndTime(rs.getTimestamp("end_time").toLocalDateTime());
                timeSlot.setBooked(rs.getBoolean("is_booked"));
                timeSlots.add(timeSlot);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving time slots", e);
        }
        return timeSlots;    }

    @Override
    public void updateCategoryFlag(int timeSlotId, boolean newFlag) {
        try(Connection connection = dataSource.getConnection();
        PreparedStatement stmt = connection.prepareStatement(QUERY_NEW_FLAG)) {

            stmt.setBoolean(1, newFlag);
            stmt.setInt(2, timeSlotId);

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
