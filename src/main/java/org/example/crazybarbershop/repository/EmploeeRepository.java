package org.example.crazybarbershop.repository;

import org.example.crazybarbershop.models.Emploee;
import org.example.crazybarbershop.models.TimeSlot;
import org.example.crazybarbershop.models.User;

import java.util.List;

public interface EmploeeRepository {

    void save(Emploee emploee);

    List<Emploee> findAll();

    Emploee findById(String id);

    void delete(String id);


}
