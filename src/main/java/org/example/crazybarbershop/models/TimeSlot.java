package org.example.crazybarbershop.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeSlot {

    private long id;

    private Employee employee;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private boolean isBooked;

}