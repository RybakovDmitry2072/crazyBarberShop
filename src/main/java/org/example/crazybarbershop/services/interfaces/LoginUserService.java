package org.example.crazybarbershop.services.interfaces;

import org.example.crazybarbershop.dto.UserDto;

import java.util.Optional;

public interface LoginUserService {

    UserDto login(String login, String password);

}
