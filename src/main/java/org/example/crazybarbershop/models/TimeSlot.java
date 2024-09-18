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

    private Long id;            // Уникальный идентификатор слота

    private Long employeeId;     // Идентификатор сотрудника (связь с Employee)

    private LocalDateTime startTime; // Время начала

    private LocalDateTime endTime;   // Время окончания

    private boolean isBooked;    // Флаг, забронирован ли слот

}