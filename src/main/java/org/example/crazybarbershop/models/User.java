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

    private String urlImg;

    public enum Role {
        ADMIN, USER
    }
    public int getRoleId(User.Role role) {
        switch (role) {
            case ADMIN:
                return 1;
            case USER:
                return 2;
            default:
                throw new IllegalArgumentException("Unknown role: " + role);
        }
    }

    public boolean isAdmin(){
        return this.role == Role.ADMIN;
    }
}



