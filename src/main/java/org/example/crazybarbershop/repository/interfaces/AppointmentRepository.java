package org.example.crazybarbershop.repository.interfaces;

import org.example.crazybarbershop.models.Appointment;

import java.util.List;

public interface AppointmentRepository {

    void save(Appointment appointment);

    List<Appointment> findAll();

    Appointment findById(String id);

    void delete(String id);

}
