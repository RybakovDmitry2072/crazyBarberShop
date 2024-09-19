package org.example.crazybarbershop.services.impl;

import lombok.AllArgsConstructor;
import org.example.crazybarbershop.data.UserRegistrationData;
import org.example.crazybarbershop.map.UserMapperData;
import org.example.crazybarbershop.models.User;
import org.example.crazybarbershop.repository.UserRepository;
import org.example.crazybarbershop.services.interfaces.RegistrationUserService;
import org.example.crazybarbershop.util.HashPassword;
import org.example.crazybarbershop.validators.UsersValidation.UserValidator;

import javax.xml.validation.Validator;

//TODO : ...

@AllArgsConstructor
public class RegistrationUserServiceimpl implements RegistrationUserService {

    private UserRepository userRepository;

    @Override
    public void registerUser(UserRegistrationData data) {
        User user = UserMapperData.mapFrom(data);
        user.setPassword(HashPassword.hashPassword(user.getPassword()));
        userRepository.save(user);


    }

}
