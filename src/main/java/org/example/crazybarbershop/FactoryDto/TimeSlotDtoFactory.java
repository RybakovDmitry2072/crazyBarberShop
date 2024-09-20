package org.example.crazybarbershop.FactoryDto;

import org.example.crazybarbershop.dto.TimeSlotDto;
import org.example.crazybarbershop.models.TimeSlot;

public class TimeSlotDtoFactory {

    public static TimeSlotDto factoryDto(TimeSlot timeSlot){
        return TimeSlotDto.builder()
                .startTime(timeSlot.getStartTime())
                .id(timeSlot.getEmployeeId())
                .build();
    }
}
