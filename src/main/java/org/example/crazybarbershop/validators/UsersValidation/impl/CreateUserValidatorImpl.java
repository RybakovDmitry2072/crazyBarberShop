package org.example.crazybarbershop.validators.UsersValidation.impl;

import lombok.AllArgsConstructor;
import org.example.crazybarbershop.data.UserRegistrationData;
import org.example.crazybarbershop.repository.UserRepository;
import org.example.crazybarbershop.util.Error;
import org.example.crazybarbershop.validators.UsersValidation.ValidationResult;
import org.example.crazybarbershop.validators.UsersValidation.interfecies.CreateUserValidator;

import java.util.regex.Pattern;

@AllArgsConstructor
public class CreateUserValidatorImpl implements CreateUserValidator {

    private UserRepository userRepository;

    // Регулярное выражение для международного формата
    private static final Pattern NUMBER_PATTERN = Pattern.compile("^\\+?[0-9]{7,15}$");

    // Регулярное выражение для проверки корректности email
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    );

    // Регулярное выражение для проверки пароля
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$"
    );

    // Регулярное выражение для проверки логина
    private static final Pattern LOGIN_PATTERN = Pattern.compile(
            "^[a-zA-Z0-9._-]{3,}$"
    );


    @Override
    public ValidationResult isValid(UserRegistrationData data) {

        ValidationResult validationResult = new ValidationResult();

        // Проверка email на null и валидность по регулярному выражению
        if (data.getEmail() == null || !EMAIL_PATTERN.matcher(data.getEmail()).matches()) {
            validationResult.add(Error.of("invalid.email", "Email invalid"));
        }

        // Проверка логина на null и валидность по регулярному выражению
        if (data.getLogin() == null || !LOGIN_PATTERN.matcher(data.getLogin()).matches()) {
            validationResult.add(Error.of("invalid.login", "Login invalid"));
        }

        // Проверка номера телефона на null и валидность по регулярному выражению
        if (data.getPhoneNumber() == null || !NUMBER_PATTERN.matcher(data.getPhoneNumber()).matches()) {
            validationResult.add(Error.of("invalid.phoneNumber", "Phone number invalid"));
        }

        // Проверка существования пользователя с указанным логином
        if (userRepository.findByLogin(data.getLogin()) != null) {
            validationResult.add(Error.of("duplicate.login", "A user with this login already exists"));
        }

        //TODO : доделать с емаил
        if (userRepository.findByLogin(data.getEmail()) != null) {
            validationResult.add(Error.of("duplicate.login", "A user with this email already exists"));
        }



        return validationResult;

    }
}
