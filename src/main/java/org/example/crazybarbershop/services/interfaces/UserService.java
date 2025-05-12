package org.example.crazybarbershop.services.interfaces;

import org.example.crazybarbershop.data.UserRegistrationData;
import org.example.crazybarbershop.dto.UserDto;
import org.example.crazybarbershop.models.User;

import java.util.List;

public interface UserService {

    User auth(String login, String password);

    User registerUser(UserRegistrationData data);

    User findById(int userId);

    List<User> findAll();

    void updateUser(User user);

    void deleteUser(int userId);


}