package org.example.crazybarbershop.services.impl;

import lombok.AllArgsConstructor;
import org.example.crazybarbershop.repository.interfaces.TimeSlotRepository;
import org.example.crazybarbershop.services.interfaces.TimeSlotService;

@AllArgsConstructor
public class TimeSlotServiceImpl implements TimeSlotService {

    private TimeSlotRepository timeSlotRepositoryэ;

    @Override
    public void updateCategoryFlag(int timeSlotid,boolean newFlag) {
        timeSlotRepositoryэ.updateCategoryFlag(timeSlotid, newFlag);

    }
}
