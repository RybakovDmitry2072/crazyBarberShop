package org.example.crazybarbershop.map;

import org.example.crazybarbershop.data.UserRegistrationData;
import org.example.crazybarbershop.models.User;
import org.example.crazybarbershop.util.LocalDateFormatter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserMapperData  {


    public static User mapFrom(UserRegistrationData data) {
        return User.builder()
                .name(data.getName())
                .surname(data.getSurname())
                .login(data.getLogin())
                .email(data.getEmail())
                .password(data.getPassword())
                .phoneNumber(data.getPhoneNumber())
                .birthday(LocalDateFormatter.format(data.getBirthday()))
                .gender(data.getGender())
                .build();

    }
}
