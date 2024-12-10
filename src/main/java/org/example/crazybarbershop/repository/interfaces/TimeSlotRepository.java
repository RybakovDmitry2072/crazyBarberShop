package org.example.crazybarbershop.repository.interfaces;

import org.example.crazybarbershop.models.TimeSlot;

import java.util.List;

public interface TimeSlotRepository {

    List<TimeSlot> findAvailableSlotsByEmployeeId(int employeeId);

    void update(TimeSlot timeSlot);
}
