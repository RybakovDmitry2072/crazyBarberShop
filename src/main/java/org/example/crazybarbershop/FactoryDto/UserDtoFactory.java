package org.example.crazybarbershop.FactoryDto;

import org.example.crazybarbershop.dto.UserDto;
import org.example.crazybarbershop.models.User;

public class UserDtoFactory {

    public static UserDto factory(User user){

        return UserDto.builder()
                .login(user.getLogin())
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .surname(user.getName())
                .gender(user.getGender())
                .birthday(user.getBirthday())
                .password(user.getPassword())
                .build();

    }

}
