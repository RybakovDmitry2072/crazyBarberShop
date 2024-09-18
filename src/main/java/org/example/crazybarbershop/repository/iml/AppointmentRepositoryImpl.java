package org.example.crazybarbershop.repository.iml;

import org.example.crazybarbershop.models.Appointment;
import org.example.crazybarbershop.repository.AppointmentRepository;

import javax.sql.DataSource;
import java.util.List;

public class AppointmentRepositoryImpl implements AppointmentRepository {

    private DataSource dataSource;

    public AppointmentRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void save(Appointment appointment) {

    }

    @Override
    public List<Appointment> findAll() {
        return List.of();
    }

    @Override
    public Appointment findById(String id) {
        return null;
    }
}
