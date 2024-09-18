package org.example.crazybarbershop.services.impl;

import org.example.crazybarbershop.data.UserRegistrationData;
import org.example.crazybarbershop.repository.UserRepository;
import org.example.crazybarbershop.services.interfaces.RegistrationUserService;
import org.example.crazybarbershop.validators.UsersValidation.UserValidator;

public class RegistrationUserServiceimpl implements RegistrationUserService {
    private UserValidator userValidator;
    private UserRepository userRepository;


    public RegistrationUserServiceimpl(UserValidator userValidator, UserRepository userRepository){
        this.userValidator = userValidator;
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(UserRegistrationData data) {
        //TODO :Логика валидации..


    }
}
