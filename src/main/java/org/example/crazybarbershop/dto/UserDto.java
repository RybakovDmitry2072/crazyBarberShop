package org.example.crazybarbershop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private int id;

    private String name;

    private String surname;

    private String login;

    private String phoneNumber;

    private String email;

    private String password;

    private LocalDate birthday;

    private String gender;

    private String urlImg;

}
