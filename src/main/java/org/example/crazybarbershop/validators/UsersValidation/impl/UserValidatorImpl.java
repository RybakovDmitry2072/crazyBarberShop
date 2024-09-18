package org.example.crazybarbershop.validators.UsersValidation.impl;

import org.example.crazybarbershop.validators.UsersValidation.UserValidator;

import java.util.regex.Pattern;

public class UserValidatorImpl implements UserValidator {

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
    public boolean emailValidator(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }

    @Override
    public boolean passwordValidator(String password) {
        return password != null && PASSWORD_PATTERN.matcher(password).matches();
    }

    @Override
    public boolean loginValidator(String login) {
        return login != null && LOGIN_PATTERN.matcher(login).matches();
    }

    @Override
    public boolean phoneNumberValidator(String phoneNumber) {
        return phoneNumber!=null && NUMBER_PATTERN.matcher(phoneNumber).matches();
    }
}
