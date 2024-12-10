package org.example.crazybarbershop.repository.iml;

import org.example.crazybarbershop.models.Haircut;
import org.example.crazybarbershop.repository.interfaces.HaircutRepository;

import javax.sql.DataSource;
import java.util.List;

public class HaircutRepositoryImpl implements HaircutRepository {
    private DataSource dataSource;

    public HaircutRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void save(Haircut haircut) {

    }

    @Override
    public List<Haircut> findAll() {
        return List.of();
    }

    @Override
    public Haircut findById(String id) {
        return null;
    }
}
