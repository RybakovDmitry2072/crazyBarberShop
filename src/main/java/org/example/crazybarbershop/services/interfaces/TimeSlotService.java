package org.example.crazybarbershop.services.interfaces;

import org.example.crazybarbershop.dto.TimeSlotDto;

public interface TimeSlotService {

    void updateCategoryFlag(int timeSlotId, boolean newFlag);

    TimeSlotDto getTimeSlotById(int timeSlotId);

}
