package org.example.crazybarbershop.FactoryDto;

import org.example.crazybarbershop.dto.EmployeeDto;
import org.example.crazybarbershop.models.Employee;

public class EmployeeDtoFactory {

    public static EmployeeDto factoryDto(Employee employee){

        return EmployeeDto.builder()
                .id(employee.getId())
                .name(employee.getName())
                .gender(employee.getGender())
                .surname(employee.getName())
                .position(String.valueOf(employee.getPosition()))
                .urlImage(employee.getUrlImage())
                .about(employee.getAbout())
                .experience(employee.getExperience())
                .build();

    }
}
