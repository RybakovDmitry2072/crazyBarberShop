package org.example.crazybarbershop.validators.UsersValidation;

public interface UserValidator {

    boolean emailValidator(String email);

    boolean passwordValidator(String password);

    boolean loginValidator(String login);

    boolean phoneNumberValidator(String phoneNumber);
}
