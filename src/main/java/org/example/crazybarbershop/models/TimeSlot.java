package org.example.crazybarbershop.models;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimeSlot {

    private int id;

    @ToString.Exclude
    private Employee employee;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private boolean isBooked;

}