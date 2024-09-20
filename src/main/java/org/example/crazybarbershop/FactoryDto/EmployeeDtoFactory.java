package org.example.crazybarbershop.FactoryDto;

import org.example.crazybarbershop.dto.EmploeeDto;
import org.example.crazybarbershop.models.Emploee;
import org.example.crazybarbershop.models.User;

public class EmployeeDtoFactory {

    public static EmploeeDto factoryDto(Emploee emploee){

        return EmploeeDto.builder()
                .name(emploee.getName())
                .id(emploee.getId())
                .gender(emploee.getGender())
                .surname(emploee.getName())
                .position(emploee.getPosition())
                .urlImage(emploee.getUrlImage())
                .build();

    }
}
