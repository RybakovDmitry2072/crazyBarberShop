package org.example.crazybarbershop.services.interfaces;

import org.example.crazybarbershop.dto.TimeSlotDto;
import org.example.crazybarbershop.models.TimeSlot;
import java.util.List;

public interface TimeSlotService {

    void updateCategoryFlag(int timeSlotId, boolean newFlag);

    TimeSlotDto getTimeSlotById(int timeSlotId);

    List<TimeSlot> getAllTimeSlots();

    void delete(int id);

    void update(TimeSlot timeSlot);

    void saveTimeSlot(TimeSlot timeSlot);

}
