package org.example.crazybarbershop.FactoryDto;

import org.example.crazybarbershop.dto.EmploeeDto;
import org.example.crazybarbershop.models.Employee;

public class EmployeeDtoFactory {

    public static EmploeeDto factoryDto(Employee employee){

        return EmploeeDto.builder()
                .name(employee.getName())
                .id(employee.getId())
                .gender(employee.getGender())
                .surname(employee.getName())
                .position(String.valueOf(employee.getPosition()))
                .urlImage(employee.getUrlImage())
                .build();

    }
}
