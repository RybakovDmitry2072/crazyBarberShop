package org.example.crazybarbershop.services.interfaces;

import org.example.crazybarbershop.data.AppointmentData;

import java.util.List;

public interface AppointmentService {

    void bookAppointment(int categoryId, int emloyeeId, int timeSlotId);

//    List<String> getAllHaircutType();

}
