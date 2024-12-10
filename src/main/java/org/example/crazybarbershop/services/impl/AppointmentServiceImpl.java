package org.example.crazybarbershop.services.impl;

import org.example.crazybarbershop.data.AppointmentData;
import org.example.crazybarbershop.repository.interfaces.AppointmentRepository;
import org.example.crazybarbershop.services.interfaces.AppointmentService;

public class AppointmentServiceImpl implements AppointmentService {
    private AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public void bookAppointment(AppointmentData data) {

    }
}
