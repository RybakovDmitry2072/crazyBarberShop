package org.example.crazybarbershop.services.impl;

import lombok.AllArgsConstructor;
import org.example.crazybarbershop.Exceptions.ValidatiounException;
import org.example.crazybarbershop.data.UserRegistrationData;
import org.example.crazybarbershop.map.UserMapperData;
import org.example.crazybarbershop.models.User;
import org.example.crazybarbershop.repository.UserRepository;
import org.example.crazybarbershop.services.interfaces.RegistrationUserService;
import org.example.crazybarbershop.util.HashPassword;
import org.example.crazybarbershop.validators.UsersValidation.ValidationResult;
import org.example.crazybarbershop.validators.UsersValidation.interfecies.CreateUserValidator;

@AllArgsConstructor
public class RegistrationUserServiceimpl implements RegistrationUserService {

    private UserRepository userRepository;

    private CreateUserValidator createUserValidator;

    @Override
    public void registerUser(UserRegistrationData data) {

        ValidationResult validationResult = createUserValidator.isValid(data);

        if (!validationResult.isValid()) {

            throw new ValidatiounException(validationResult.getErrors());

        }

        User user = UserMapperData.mapFrom(data);
        user.setPassword(HashPassword.hashPassword(user.getPassword()));
        userRepository.save(user);

    }

}
