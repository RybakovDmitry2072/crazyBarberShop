package org.example.crazybarbershop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmploeeDto {

    private int id;

    private String name;

    private String surname;

    private String position;

    private String gender;

    private String urlImage;

    private String phoneNumber;


}
