package org.example.crazybarbershop.services.interfaces;

import org.example.crazybarbershop.data.UserRegistrationData;
import org.example.crazybarbershop.dto.UserDto;
import org.example.crazybarbershop.models.User;

public interface UserService {

    User auth(String login, String password);

    User registerUser(UserRegistrationData data);

    UserDto getUserById(int userId);


}