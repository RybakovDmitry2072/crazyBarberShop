package org.example.crazybarbershop.repository.interfaces;

import org.example.crazybarbershop.models.Haircut;

import java.util.List;

public interface HaircutRepository {

    void save(Haircut haircut);

    List<Haircut> findAll();

    Haircut findById(String id);

    void delete(String id);

}
