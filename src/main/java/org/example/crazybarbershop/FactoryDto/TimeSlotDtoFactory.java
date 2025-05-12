package org.example.crazybarbershop.FactoryDto;

import org.example.crazybarbershop.dto.TimeSlotDto;
import org.example.crazybarbershop.models.TimeSlot;
import org.example.crazybarbershop.util.LocalDateTimeFormatter;

public class TimeSlotDtoFactory {

    public static TimeSlotDto factoryDto(TimeSlot timeSlot){
        return TimeSlotDto.builder()
                .id(timeSlot.getId())
                .startTime(LocalDateTimeFormatter.formatToString(timeSlot.getStartTime()))
                .build();
    }
}
