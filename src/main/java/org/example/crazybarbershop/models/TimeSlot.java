package org.example.crazybarbershop.models;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeSlot {

    private int id;

    private int employeeId;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private boolean isBooked;

    public boolean getIsBooked() {
        return isBooked;
    }

}