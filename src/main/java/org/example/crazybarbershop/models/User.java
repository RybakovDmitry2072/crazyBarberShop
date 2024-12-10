package org.example.crazybarbershop.models;

import lombok.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int id;

    private String name;

    private String surname;

    private String login;

    private String phoneNumber;

    private String email;

    private String password;

    private LocalDate birthday;

    private String gender;

    private Role role;

    public enum Role {
        ADMIN, USER
    }

}



