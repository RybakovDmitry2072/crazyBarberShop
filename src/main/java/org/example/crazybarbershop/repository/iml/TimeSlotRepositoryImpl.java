package org.example.crazybarbershop.repository.iml;

import org.example.crazybarbershop.models.TimeSlot;
import org.example.crazybarbershop.repository.interfaces.TimeSlotRepository;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
