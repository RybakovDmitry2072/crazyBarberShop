package org.example.crazybarbershop.repository.iml;

import org.example.crazybarbershop.models.Emploee;
import org.example.crazybarbershop.models.TimeSlot;
import org.example.crazybarbershop.models.User;
import org.example.crazybarbershop.repository.EmploeeRepository;

import javax.sql.DataSource;
import java.util.List;

public class EmploeeRepositoryIml implements EmploeeRepository {

    private DataSource dataSource;

    public EmploeeRepositoryIml(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public List<TimeSlot> findAllFreeTimeSlots() {
        return List.of();
    }

    @Override
    public void save(Emploee emploee) {

    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public User findById(String id) {
        return null;
    }
}
