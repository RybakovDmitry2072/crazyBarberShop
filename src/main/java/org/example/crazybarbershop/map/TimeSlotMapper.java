package org.example.crazybarbershop.map;

import org.example.crazybarbershop.models.TimeSlot;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TimeSlotMapper  {

    public static TimeSlot mapRow(ResultSet rs) throws SQLException {

        TimeSlot slot = new TimeSlot();
        slot.setId(rs.getLong("id"));
        slot.setEmployeeId((long) rs.getInt("employee_id"));
        slot.setStartTime(rs.getTimestamp("start_time").toLocalDateTime());
        slot.setEndTime(rs.getTimestamp("end_time").toLocalDateTime());
        slot.setBooked(rs.getBoolean("is_booked"));
        return slot;

    }
}
