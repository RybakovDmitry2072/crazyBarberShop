package org.example.crazybarbershop.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationData {

    private String name;

    private String surname;

    private String login;

    private String phoneNumber;

    private String email;

    private String password;

    private String birthday;

    private String gender;

}
