package org.example.crazybarbershop.repository;

import org.example.crazybarbershop.models.Appointment;
import org.example.crazybarbershop.models.Category;
import org.example.crazybarbershop.models.Haircut;

import java.util.List;

public interface AppointmentRepository {

    void save(Appointment appointment);

    List<Appointment> findAll();

    Appointment findById(String id);

    void delete(String id);

}
