package org.example.crazybarbershop.services.impl;

import lombok.AllArgsConstructor;
import org.example.crazybarbershop.models.User;
import org.example.crazybarbershop.repository.UserRepository;
import org.example.crazybarbershop.services.interfaces.LoginUserService;
import org.example.crazybarbershop.util.HashPassword;

@AllArgsConstructor
public class LoginUserServiceImpl implements LoginUserService {

    UserRepository userRepository;

    @Override
    public boolean login(String login, String password) {
        User user = userRepository.findByLogin(login);
        if(user != null){
            if(HashPassword.hashPassword(password).equals(user.getPassword())){
                return true;
            }
        }
        return false;
    }
}
