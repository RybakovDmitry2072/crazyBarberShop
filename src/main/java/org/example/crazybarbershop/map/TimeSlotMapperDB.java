package org.example.crazybarbershop.map;

import org.example.crazybarbershop.models.TimeSlot;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TimeSlotMapperDB {

    public static TimeSlot mapRow(ResultSet rs) throws SQLException {
        return TimeSlot.builder()
                .id(rs.getInt("time_slot_id"))
                .startTime(rs.getTimestamp("start_time").toLocalDateTime())
                .endTime(rs.getTimestamp("end_time").toLocalDateTime())
                .isBooked(rs.getBoolean("is_booked"))
                .build();
    }
}
