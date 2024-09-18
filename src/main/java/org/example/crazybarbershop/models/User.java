package org.example.crazybarbershop.models;

import lombok.*;

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

}
