package org.example.crazybarbershop.repository.interfaces;

import org.example.crazybarbershop.models.Employee;

import java.util.List;
import java.util.Optional;

public interface EmploeeRepository {

    Optional<List<Employee>> findAllAvailableTimeSlots();

    Optional<List<Employee>> findAllBarber();

    void save(Employee employee);

    Optional<Employee> findById(int id);

    void delete(int id);

    List<Employee> getAllEmployee();

    void update(Employee employee);


}
