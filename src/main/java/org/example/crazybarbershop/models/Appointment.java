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
public class Appointment {

    private int id; // Уникальный идентификатор записи

    private int userId; // Идентификатор пользователя

    private Category categoryId; // Категория

    private int haircutId; // Идентификатор стрижки

    private int EmploeeId; //Индефикатор работника

    private TimeSlot timeSlot; // Время записи

}
