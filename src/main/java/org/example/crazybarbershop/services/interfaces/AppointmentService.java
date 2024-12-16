package org.example.crazybarbershop.services.interfaces;

import org.example.crazybarbershop.models.Appointment;

public interface AppointmentService {

    void bookAppointment(int categoryId, int emloyeeId, int timeSlotId);

    public Appointment getUsAppointmentByUserUd(int id);


}
