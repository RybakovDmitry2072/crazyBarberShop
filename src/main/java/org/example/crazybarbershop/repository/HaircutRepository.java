package org.example.crazybarbershop.repository;

import org.example.crazybarbershop.models.Emploee;
import org.example.crazybarbershop.models.Haircut;
import org.example.crazybarbershop.models.User;

import java.util.List;

public interface HaircutRepository {

    void save(Haircut haircut);

    List<Haircut> findAll();

    Haircut findById(String id);

    void delete(String id);

}
