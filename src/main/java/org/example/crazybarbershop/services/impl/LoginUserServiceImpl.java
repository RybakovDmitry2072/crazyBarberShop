package org.example.crazybarbershop.services.impl;

import lombok.AllArgsConstructor;
import org.example.crazybarbershop.models.User;
import org.example.crazybarbershop.repository.interfaces.UserRepository;
import org.example.crazybarbershop.services.interfaces.LoginUserService;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Optional;

@AllArgsConstructor
public class LoginUserServiceImpl implements LoginUserService {

    private UserRepository userRepository;

    @Override
    public boolean login(String login, String password) {

        Optional<User> user = userRepository.findByLogin(login);

        if (user.isPresent() && BCrypt.checkpw(password, user.get().getPassword())){
            return true;
        }

        throw new IllegalArgumentException("Неверный логин или пароль");

    }
}
