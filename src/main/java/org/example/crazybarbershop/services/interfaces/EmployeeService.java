package org.example.crazybarbershop.services.interfaces;

import org.example.crazybarbershop.dto.EmployeeDto;
import org.example.crazybarbershop.dto.TimeSlotDto;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    List<EmployeeDto> getAllBarberEmployees();

    EmployeeDto getEmployeeByid(int employeeId);

    Map<EmployeeDto, List<TimeSlotDto>> getFreeTimeForAllEmployee();
}
