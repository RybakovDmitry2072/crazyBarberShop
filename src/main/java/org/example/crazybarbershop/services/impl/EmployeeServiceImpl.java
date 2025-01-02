package org.example.crazybarbershop.services.impl;

import lombok.AllArgsConstructor;
import org.example.crazybarbershop.FactoryDto.EmployeeDtoFactory;
import org.example.crazybarbershop.FactoryDto.TimeSlotDtoFactory;
import org.example.crazybarbershop.dto.EmployeeDto;
import org.example.crazybarbershop.dto.TimeSlotDto;
import org.example.crazybarbershop.models.Employee;
import org.example.crazybarbershop.models.TimeSlot;
import org.example.crazybarbershop.repository.interfaces.EmploeeRepository;
import org.example.crazybarbershop.services.interfaces.EmployeeService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmploeeRepository employeeRepository;

    @Override
    public List<EmployeeDto> getAllBarberEmployees() {
        Optional<List<Employee>> employeeList = employeeRepository.findAllBarber();
        try {
            return employeeList.get().stream()
                    .map(EmployeeDtoFactory::factoryDto)
                    .collect(Collectors.toList());
        }catch (RuntimeException e) {
            throw new IllegalStateException("Not found employess", e);
        }
    }

    @Override
    public EmployeeDto getEmployeeByid(int employeeId) {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() ->
                new IllegalStateException("not found employee by id"));

        EmployeeDto employeeDto = EmployeeDtoFactory.factoryDto(employee);

        return employeeDto;
    }


    @Override
    public Map<EmployeeDto, List<TimeSlotDto>> getFreeTimeForAllEmployee() {
        Map<EmployeeDto, List<TimeSlotDto>> employeeTimeSlotsMapDto = new HashMap<>();
        List<Employee> employees = employeeRepository.findAllAvailableTimeSlots()
                .orElseThrow( () ->
                        new IllegalStateException("No employees found"));

        for (Employee employee : employees) {
            List<TimeSlot> timeSlots = employee.getTimeSlotList();
            List<TimeSlotDto> timeSlotDtoList = timeSlots.stream()
                    .map(TimeSlotDtoFactory::factoryDto)
                    .collect(Collectors.toList());
            EmployeeDto employeeDto = EmployeeDtoFactory.factoryDto(employee);
            employeeTimeSlotsMapDto.put(employeeDto, timeSlotDtoList);
        }


        return employeeTimeSlotsMapDto;

    }
}
