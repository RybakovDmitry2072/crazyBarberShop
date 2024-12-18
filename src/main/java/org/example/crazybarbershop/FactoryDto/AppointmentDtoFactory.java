package org.example.crazybarbershop.FactoryDto;

import org.example.crazybarbershop.dto.AppointmentDto;
import org.example.crazybarbershop.models.Appointment;

import java.time.LocalDateTime;

public class AppointmentDtoFactory {

    public static AppointmentDto factoryDto(int id, String username, String categoriaName, String employeeName, LocalDateTime timeSlot, String status) {
        return AppointmentDto.builder()
                .id(id)
                .username(username)
                .categoryName(categoriaName)
                .employeeName(employeeName)
                .timeSlot(timeSlot)
                .status(status)
                .build();
    }
}
