package org.example.crazybarbershop.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.crazybarbershop.models.Category;
import org.example.crazybarbershop.models.TimeSlot;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentData {

    private int id; // Уникальный идентификатор записи

    private int userId; // Идентификатор пользователя

    private Category categoryId; // Категория

    private int haircutId; // Идентификатор стрижки

    private int EmploeeId; //Индефикатор работника

    private TimeSlot timeSlot; // Время записи

}
