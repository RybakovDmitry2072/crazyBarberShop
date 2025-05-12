package org.example.crazybarbershop.services.interfaces;

import org.example.crazybarbershop.dto.EmployeeDto;
import org.example.crazybarbershop.dto.TimeSlotDto;
import org.example.crazybarbershop.models.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    List<EmployeeDto> getAllBarberEmployees();

    EmployeeDto getEmployeeByid(int employeeId);

    Map<EmployeeDto, List<TimeSlotDto>> getFreeTimeForAllEmployee();

    void delete(int id);

    void update(Employee employee);

    List<Employee> getAllEmployee();

    void save(Employee employee);
}
