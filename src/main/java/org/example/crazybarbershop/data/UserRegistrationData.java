package org.example.crazybarbershop.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationData {

    private String name;

    private String firstname;

    private String login;

    private String phoneNumber;

    private String email;

    private String password;

}
