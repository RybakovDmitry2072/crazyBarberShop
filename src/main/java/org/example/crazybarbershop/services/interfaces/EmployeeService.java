package org.example.crazybarbershop.services.interfaces;

import org.example.crazybarbershop.dto.EmploeeDto;
import org.example.crazybarbershop.dto.TimeSlotDto;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    List<EmploeeDto> getAllEmployees();

    List<TimeSlotDto> getFreeTimeSlot(int EmployeeId);

    Map<EmploeeDto, List<TimeSlotDto>> getFreeTimeForAllEmployee();
}
