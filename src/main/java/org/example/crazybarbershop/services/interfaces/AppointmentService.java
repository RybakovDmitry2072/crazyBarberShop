package org.example.crazybarbershop.services.interfaces;

import org.example.crazybarbershop.dto.AppointmentDto;
import org.example.crazybarbershop.models.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {

    void bookAppointment(int categoryId, int emloyeeId, int timeSlotId);

    List<Appointment> getAppointmentsByUserUd(int id);

    boolean isAppointmentCompleted(Appointment appointment);

}
