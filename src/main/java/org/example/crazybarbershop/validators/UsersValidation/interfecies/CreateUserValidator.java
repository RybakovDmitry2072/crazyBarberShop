package org.example.crazybarbershop.validators.UsersValidation.interfecies;

import org.example.crazybarbershop.data.UserRegistrationData;
import org.example.crazybarbershop.validators.UsersValidation.ValidationResult;

public interface CreateUserValidator {

    ValidationResult isValid(UserRegistrationData data);

}
