package org.example.crazybarbershop.services.impl;

import lombok.AllArgsConstructor;
import org.example.crazybarbershop.Exceptions.ValidatiounException;
import org.example.crazybarbershop.data.UserRegistrationData;
import org.example.crazybarbershop.map.UserMapperData;
import org.example.crazybarbershop.models.User;
import org.example.crazybarbershop.repository.interfaces.UserRepository;
import org.example.crazybarbershop.services.interfaces.RegistrationUserService;
import org.example.crazybarbershop.validators.UsersValidation.ValidationResult;
import org.example.crazybarbershop.validators.UsersValidation.impl.UserRegistrationValidator;
import org.mindrot.jbcrypt.BCrypt;

@AllArgsConstructor
public class RegistrationUserServiceImpl implements RegistrationUserService {

    private UserRepository userRepository;

    @Override
    public void registerUser(UserRegistrationData data) {

        ValidationResult validationResult = UserRegistrationValidator.getInstance().isValid(data);

        if (!validationResult.isValid()) {

            throw new ValidatiounException(validationResult.getErrors());

        }

        User user = UserMapperData.mapFrom(data);
        user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt()));
        userRepository.save(user);

    }

}
