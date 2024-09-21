package org.example.crazybarbershop.services.impl;

import lombok.AllArgsConstructor;
import org.example.crazybarbershop.FactoryDto.UserDtoFactory;
import org.example.crazybarbershop.dto.UserDto;
import org.example.crazybarbershop.models.User;
import org.example.crazybarbershop.repository.UserRepository;
import org.example.crazybarbershop.services.interfaces.LoginUserService;
import org.example.crazybarbershop.util.HashPassword;

import java.util.Optional;

@AllArgsConstructor
public class LoginUserServiceImpl implements LoginUserService {

    private UserRepository userRepository;

    @Override
    public UserDto login(String login, String password) {

        User user = userRepository.findByLogin(login);

        if (user != null && HashPassword.hashPassword(password).equals(user.getPassword())) {
            return UserDtoFactory.factory(user);
        }

        throw new IllegalArgumentException("Неверный логин или пароль");
    }
}
