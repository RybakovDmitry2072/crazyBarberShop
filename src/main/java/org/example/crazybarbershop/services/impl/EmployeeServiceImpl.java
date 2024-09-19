package org.example.crazybarbershop.services.impl;

import lombok.AllArgsConstructor;
import org.example.crazybarbershop.FactoryDto.EmployeeDtoFactory;
import org.example.crazybarbershop.dto.EmploeeDto;
import org.example.crazybarbershop.models.Emploee;
import org.example.crazybarbershop.repository.EmploeeRepository;
import org.example.crazybarbershop.services.interfaces.EmployeeService;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmploeeRepository emploeeRepository;

    private EmployeeDtoFactory employeeDtoFactory;

    @Override
    public List<EmploeeDto> getAllEmployees() {
        List<Emploee> emploeeList = emploeeRepository.findAll();
        return emploeeList.stream()
                .map(EmployeeDtoFactory::factoryDto)
                .collect(Collectors.toList());
    }
}
