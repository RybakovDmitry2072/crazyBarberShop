package org.example.crazybarbershop.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.crazybarbershop.models.Appointment;
import org.example.crazybarbershop.models.TimeSlot;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentData {

    private String category; // Категория

    private int EmploeeId; //Индефикатор работника

    private TimeSlot timeSlot; // Время записи

}
