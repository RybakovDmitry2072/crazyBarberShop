package org.example.crazybarbershop.repository.interfaces;

import org.example.crazybarbershop.models.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentRepository {

    void save(Appointment appointment);

    List<Appointment> findAll();

    Optional<Appointment> findById(int id);

    Optional<List<Appointment>> findAppointmentByUserId(int id);

    void delete(int id);

}
