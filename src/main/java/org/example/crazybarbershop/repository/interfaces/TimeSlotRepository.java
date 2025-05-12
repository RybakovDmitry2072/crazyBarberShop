package org.example.crazybarbershop.repository.interfaces;

import org.example.crazybarbershop.models.TimeSlot;

import java.util.List;
import java.util.Optional;

public interface TimeSlotRepository {

    void delete(int id);

    void update(TimeSlot timeSlot);

    List<TimeSlot> getAllTimeSlots();

    void updateCategoryFlag(int timeSlotId, boolean newFlag);

    Optional<TimeSlot> getTimeSlotByid(int timeSlotId);

    void save(TimeSlot timeSlot);
}
