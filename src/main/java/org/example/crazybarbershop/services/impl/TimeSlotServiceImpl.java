package org.example.crazybarbershop.services.impl;

import lombok.AllArgsConstructor;
import org.example.crazybarbershop.FactoryDto.TimeSlotDtoFactory;
import org.example.crazybarbershop.dto.TimeSlotDto;
import org.example.crazybarbershop.models.TimeSlot;
import org.example.crazybarbershop.repository.interfaces.TimeSlotRepository;
import org.example.crazybarbershop.services.interfaces.TimeSlotService;

@AllArgsConstructor
public class TimeSlotServiceImpl implements TimeSlotService {

    private TimeSlotRepository timeSlotRepository;

    @Override
    public void updateCategoryFlag(int timeSlotid,boolean newFlag) {
        timeSlotRepository.updateCategoryFlag(timeSlotid, newFlag);

    }

    @Override
    public TimeSlotDto getTimeSlotById(int timeSlotId) {
        TimeSlot timeSlot = timeSlotRepository.getTimeSlotByid(timeSlotId).orElseThrow(() ->
                new IllegalStateException("not found time slot"));

        TimeSlotDto timeSlotDto = TimeSlotDtoFactory.factoryDto(timeSlot);

        return timeSlotDto;
    }


}
