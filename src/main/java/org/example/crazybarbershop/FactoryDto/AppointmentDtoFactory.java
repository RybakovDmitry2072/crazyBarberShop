package org.example.crazybarbershop.FactoryDto;

import org.example.crazybarbershop.dto.AppointmentDto;

import java.time.LocalDateTime;

public class AppointmentDtoFactory {

    public static AppointmentDto factoryDto(int id, String username, String categoriaName, String employeeName, LocalDateTime timeSlot) {
        return AppointmentDto.builder()
                .id(id)
                .username(username)
                .categoriaName(categoriaName)
                .employeeName(employeeName)
                .timeSlot(timeSlot)
                .build();
    }

}
