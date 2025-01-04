package org.example.crazybarbershop.services.impl;

import lombok.AllArgsConstructor;
import org.example.crazybarbershop.FactoryDto.TimeSlotDtoFactory;
import org.example.crazybarbershop.dto.TimeSlotDto;
import org.example.crazybarbershop.models.TimeSlot;
import org.example.crazybarbershop.repository.interfaces.TimeSlotRepository;
import org.example.crazybarbershop.services.interfaces.TimeSlotService;

import java.util.List;

@AllArgsConstructor
public class TimeSlotServiceImpl implements TimeSlotService {

    private TimeSlotRepository timeSlotRepository;

    @Override
    public void updateCategoryFlag(int timeSlotid,boolean newFlag) {
        timeSlotRepository.updateCategoryFlag(timeSlotid, newFlag);

    }

    @Override
    public void saveTimeSlot(TimeSlot timeSlot) {
        timeSlotRepository.save(timeSlot);
    }

    @Override
    public TimeSlotDto getTimeSlotById(int timeSlotId) {
        TimeSlot timeSlot = timeSlotRepository.getTimeSlotByid(timeSlotId).orElseThrow(() ->
                new IllegalStateException("not found time slot"));

        TimeSlotDto timeSlotDto = TimeSlotDtoFactory.factoryDto(timeSlot);

        return timeSlotDto;
    }

    @Override
    public List<TimeSlot> getAllTimeSlots() {
        return timeSlotRepository.getAllTimeSlots();
    }

    @Override
    public void delete(int id) {
        timeSlotRepository.delete(id);
    }

    @Override
    public void update(TimeSlot timeSlot) {
        timeSlotRepository.update(timeSlot);
    }
}
