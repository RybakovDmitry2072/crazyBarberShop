//package org.example.crazybarbershop.services.impl;
//
//import lombok.AllArgsConstructor;
//import org.example.crazybarbershop.FactoryDto.EmployeeDtoFactory;
//import org.example.crazybarbershop.FactoryDto.TimeSlotDtoFactory;
//import org.example.crazybarbershop.dto.EmploeeDto;
//import org.example.crazybarbershop.dto.TimeSlotDto;
//import org.example.crazybarbershop.models.Employee;
//import org.example.crazybarbershop.models.TimeSlot;
//import org.example.crazybarbershop.repository.interfaces.EmploeeRepository;
//import org.example.crazybarbershop.repository.interfaces.TimeSlotRepository;
//import org.example.crazybarbershop.services.interfaces.EmployeeService;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@AllArgsConstructor
//public class EmployeeServiceImpl implements EmployeeService {
//
//    private EmploeeRepository employeeRepository;
//
//    private TimeSlotRepository timeSlotRepository;
//
//    @Override
//    public List<EmploeeDto> getAllEmployees() {
//        Optional<List<Employee>> employeeList = employeeRepository.findAll().orElseThrow(() ->  IllegalStateException("sds"));
//        if (employeeList.isPresent())
//        return employeeList.stream()
//                .map(EmployeeDtoFactory::factoryDto)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<TimeSlotDto> getFreeTimeSlot(int employeeId) {
//        List<TimeSlot> timeSlotList = timeSlotRepository.findAvailableSlotsByEmployeeId(employeeId);
//        List<TimeSlotDto> timeSlotDtoList;
//        return timeSlotList.stream()
//                .map(TimeSlotDtoFactory::factoryDto)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public Map<EmploeeDto, List<TimeSlotDto>> getFreeTimeForAllEmployee() {
//        Map<EmploeeDto, List<TimeSlotDto>> employeeTimeSlotsMap = new HashMap<>();
//        List<EmploeeDto> employees = getAllEmployees();
//        for (EmploeeDto employee : employees) {
//            List<TimeSlotDto> timeSlots = getFreeTimeSlot(employee.getId());
//            employeeTimeSlotsMap.put(employee, timeSlots);
//        }
//        return employeeTimeSlotsMap;
//    }
//}
