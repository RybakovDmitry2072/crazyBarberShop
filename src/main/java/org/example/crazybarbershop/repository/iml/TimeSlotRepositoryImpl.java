//package org.example.crazybarbershop.repository.iml;
//
//import org.example.crazybarbershop.models.TimeSlot;
//import org.example.crazybarbershop.repository.interfaces.TimeSlotRepository;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class TimeSlotRepositoryImpl implements TimeSlotRepository {
//    private static final String QUERY = "SELECT * FROM time_slot WHERE employee_id = ? AND is_booked = FALSE";
//    private DataSource dataSource;
//
//    public TimeSlotRepositoryImpl(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    @Override
//    public List<TimeSlot> findAvailableSlotsByEmployeeId(int employeeId) {
//        List<TimeSlot> slots = new ArrayList<>();
//        try (Connection conn = dataSource.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(QUERY)) {
//
//            stmt.setInt(1, employeeId);
//            ResultSet rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                TimeSlot slot = new TimeSlot();
//                slot.setId(rs.getLong("id"));
//                slot.setEmployeeId((long) rs.getInt("employee_id"));
//                slot.setStartTime(rs.getTimestamp("start_time").toLocalDateTime());
//                slot.setEndTime(rs.getTimestamp("end_time").toLocalDateTime());
//                slot.setBooked(rs.getBoolean("is_booked"));
//                slots.add(slot);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return slots;
//    }
//
//    @Override
//    public void update(TimeSlot timeSlot) {
//
//    }
//}
